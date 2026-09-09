## Problem Statement

### Design an in memory cache system with:
- fixed capacity
- fast read/write
- automatic eviction policy

### Requirements
- `get(key)` -> `O(1)`
- `put(key, value)` -> `O(1)`
- configurable eviction policy

### Possible policies
- LRU - Least Recently Used
- MRU - Most Recently Used
- LFU - Least Frequently Used
- FIFO - First In First Out
- TTL Based - Time to live Based
- RR - Random Replacement

---

### Core Components
1. Cache - main cache operations
2. Eviction Policy - Decide which ey to remove
3. Storage - Store Key Value Pairs
4. Node - Metadata for policies
5. DoublyLinkedList - Maintain ordering
6.  HashMap - O(1) lookup