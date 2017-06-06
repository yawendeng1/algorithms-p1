# algorithms-p1

source: https://www.sigmainfy.com/blog/avoid-backwash-in-percolation.html

## Mehod 1
use virtual top and virtual bottom.

## Method 2
use two different Weighted Quick Union Union Find objects.

## Method 3
In the original Bob’s notes, it says we have to modify the API to achieve this, but actually it does not have to be like that. We create ONE WQUUF object of size N * N, and allocate a separate array of size N * N to keep the status of each site: blocked, open, connect to top, connect to bottom. I use bit operation for the status so for each site, it could have combined status like Open and connect to top.

The most important operation is open(int i, int j): we need to union the newly opened site (let’s call it site ‘S’) S with the four adjacent neighbor sites if possible. For each possible neighbor site(Let’s call it ‘neighbor’), we first call find(neighbor) to get the root of that connected component, and retrieves the status of that root (Let’s call it ‘status’), next, we do Union(S, neighbor); we do the similar operation for at most 4 times, and we do a 5th find(S) to get the root of the newly (copyright @sigmainfy) generated connected component results from opening the site S, finally we update the status of the new root by combining the old status information into the new root in constant time. I leave the details of how to combine the information to update the the status of the new root to the readers, which would not be hard to think of.

For the isFull(int i, int j), we need to find the the root site in the connected component which contains site (i, j) and check the status of the root.

For the isOpen(int i, int j) we directly return the status.

For percolates(), there is a way to make it constant time even though we do not have virtual top or bottom sites: think about why?

So the most important operation  open(int i, int j) will involve 4 union() and 5 find() API calls.
