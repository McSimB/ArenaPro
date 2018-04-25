package javax.microedition.rms;

class RecordEnumerationImpl implements RecordEnumeration, RecordListener {

    @SuppressWarnings("unused")
    private static final int NO_SUCH_RECORD = -1;
    private RecordStore recordStore;
    private RecordFilter filter;
    private RecordComparator comparator;
    private boolean beObserver;
    private int index;
    private int records[];

    RecordEnumerationImpl(RecordStore recordStore, RecordFilter filter, RecordComparator comparator, boolean keepUpdated) {
        this.recordStore = recordStore;
        this.filter = filter;
        this.comparator = comparator;
        records = new int[0];
        keepUpdated(keepUpdated);
        if (!keepUpdated)
            rebuild();
    }

    public synchronized int numRecords() {
        checkDestroyed();
        return records.length;
    }

    public synchronized byte[] nextRecord()
            throws RecordStoreException {
        checkDestroyed();
        return recordStore.getRecord(nextRecordId());
    }

    public synchronized int nextRecordId()
            throws InvalidRecordIDException {
        checkDestroyed();
        if (index == records.length - 1)
            throw new InvalidRecordIDException();
        if (index == -1)
            index = 0;
        else
            index++;
        return records[index];
    }

    public synchronized byte[] previousRecord()
            throws RecordStoreException {
        checkDestroyed();
        return recordStore.getRecord(previousRecordId());
    }

    public synchronized int previousRecordId()
            throws InvalidRecordIDException {
        checkDestroyed();
        if (index == 0 || records.length == 0)
            throw new InvalidRecordIDException();
        if (index == -1)
            index = records.length - 1;
        else
            index--;
        return records[index];
    }

    public boolean hasNextElement() {
        checkDestroyed();
        return recordStore.isOpen() && index != records.length - 1;
    }

    public boolean hasPreviousElement() {
        checkDestroyed();
        return !(records.length == 0 || !recordStore.isOpen()) && index != 0;
    }

    public void reset() {
        checkDestroyed();
        index = -1;
    }

    public void rebuild() {
        checkDestroyed();
        synchronized (recordStore.rsLock) {
            int tmp[] = recordStore.getRecordIDs();
            reFilterSort(tmp);
        }
    }

    public void keepUpdated(boolean keepUpdated) {
        checkDestroyed();
        if (keepUpdated != beObserver) {
            beObserver = keepUpdated;
            if (keepUpdated) {
                recordStore.addRecordListener(this);
                rebuild();
            } else {
                recordStore.removeRecordListener(this);
            }
        }
    }

    public boolean isKeptUpdated() {
        checkDestroyed();
        return beObserver;
    }

    public synchronized void recordAdded(RecordStore recordStore, int recordId) {
        checkDestroyed();
        synchronized (recordStore.rsLock) {
            filterAdd(recordId);
        }
    }

    public synchronized void recordChanged(RecordStore recordStore, int recordId) {
        checkDestroyed();
        int recIndex = findIndexOfRecord(recordId);
        if (recIndex < 0)
            return;
        removeRecordAtIndex(recIndex);
        synchronized (recordStore.rsLock) {
            filterAdd(recordId);
        }
    }

    public synchronized void recordDeleted(RecordStore recordStore, int recordId) {
        checkDestroyed();
        int recIndex = findIndexOfRecord(recordId);
        if (recIndex < 0) {
            return;
        } else {
            removeRecordAtIndex(recIndex);
            return;
        }
    }

    public synchronized void destroy() {
        checkDestroyed();
        if (beObserver)
            recordStore.removeRecordListener(this);
        filter = null;
        comparator = null;
        records = null;
        recordStore = null;
    }

    private void checkDestroyed() {
        if (recordStore == null)
            throw new IllegalStateException();
    }

    private void filterAdd(int recordId) {
        int insertPoint = -1;
        if (filter != null)
            try {
                if (!filter.matches(recordStore.getRecord(recordId)))
                    return;
            } catch (RecordStoreException rse) {
                return;
            }
        int newrecs[] = new int[records.length + 1];
        newrecs[0] = recordId;
        System.arraycopy(records, 0, newrecs, 1, records.length);
        records = newrecs;
        if (comparator != null)
            try {
                insertPoint = sortInsert();
            } catch (RecordStoreException rse) {
                System.out.println("Unexpected exception in filterAdd");
            }
        if (index != -1 && insertPoint != -1 && insertPoint < index)
            index++;
    }

    private int sortInsert()
            throws RecordStoreException {
        int i = 0;
        for (int j = 1; i < records.length - 1 && comparator.compare(recordStore.getRecord(records[i]), recordStore.getRecord(records[j])) == 1; j++) {
            int tmp = records[i];
            records[i] = records[j];
            records[j] = tmp;
            i++;
        }

        return i;
    }

    private int findIndexOfRecord(int recordId) {
        int recIndex = -1;
        int idx = records.length - 1;
        do {
            if (idx < 0)
                break;
            if (records[idx] == recordId) {
                recIndex = idx;
                break;
            }
            idx--;
        } while (true);
        return recIndex;
    }

    private void removeRecordAtIndex(int recIndex) {
        int tmp[] = new int[records.length - 1];
        if (recIndex < records.length) {
            System.arraycopy(records, 0, tmp, 0, recIndex);
            System.arraycopy(records, recIndex + 1, tmp, recIndex, records.length - recIndex - 1);
        } else {
            System.arraycopy(records, 0, tmp, 0, records.length - 1);
        }
        records = tmp;
        if (index != -1 && recIndex <= index)
            index--;
        else if (index == records.length)
            index--;
    }

    private void reFilterSort(int filtered[]) {
        int filteredIndex = 0;
        if (filter == null) {
            records = filtered;
        } else {
            for (int i = 0; i < filtered.length; i++)
                try {
                    if (!filter.matches(recordStore.getRecord(filtered[i])))
                        continue;
                    if (filteredIndex != i)
                        filtered[filteredIndex++] = filtered[i];
                    else
                        filteredIndex++;
                } catch (RecordStoreException rse) {
                    rse.printStackTrace();
                }

            records = new int[filteredIndex];
            System.arraycopy(filtered, 0, records, 0, filteredIndex);
        }
        if (comparator != null)
            try {
                QuickSort(records, 0, records.length - 1, comparator);
            } catch (RecordStoreException de) {
                System.out.println("Unexpected exception in reFilterSort");
            }
        reset();
    }

    private void QuickSort(int a[], int lowIndex, int highIndex, RecordComparator comparator)
            throws RecordStoreException {
        int left = lowIndex;
        int right = highIndex;
        if (highIndex > lowIndex) {
            int ind = (lowIndex + highIndex) / 2;
            int pivotIndex = a[ind];
            byte pivotData[] = recordStore.getRecord(pivotIndex);
            do {
                if (left > right)
                    break;
                for (; left < highIndex && comparator.compare(recordStore.getRecord(a[left]), pivotData) == -1; left++)
                    ;
                for (; right > lowIndex && comparator.compare(recordStore.getRecord(a[right]), pivotData) == 1; right--)
                    ;
                if (left <= right) {
                    int tmp = a[left];
                    a[left] = a[right];
                    a[right] = tmp;
                    left++;
                    right--;
                }
            } while (true);
            if (lowIndex < right)
                QuickSort(a, lowIndex, right, comparator);
            if (left < highIndex)
                QuickSort(a, left, highIndex, comparator);
        }
    }
}