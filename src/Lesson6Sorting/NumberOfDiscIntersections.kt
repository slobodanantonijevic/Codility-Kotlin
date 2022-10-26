
import java.util.Arrays


/**
 * 100/100
 * @param A
 * @return
 */
fun solution(A: IntArray): Int {
    val N = A.size
    val higherEdges = LongArray(N)
    val lowerEdges = LongArray(N)
    for (i in 0 until N) {
        higherEdges[i] = i + A[i].toLong()
        lowerEdges[i] = i - A[i].toLong()
    }
    Arrays.sort(higherEdges)
    Arrays.sort(lowerEdges)
    var counter: Long = 0
    for (i in N - 1 downTo 0) {
        val position: Int = Arrays.binarySearch(lowerEdges, higherEdges[i])
        if (position >= 0) {
            var intersects = position
            for (j in position until N) {
                if (lowerEdges[j] == higherEdges[i]) {
                    intersects++
                } else {
                    break
                }
            }
            counter += intersects.toLong()
        } else {
            counter -= (position + 1).toLong()
        }
    }
    val duplicates =
        N.toLong() * (N.toLong() + 1) / 2 // ova dva u prvom ranu preskoci pa dodaj pre slanja kao setio si se duplikata
    counter -= duplicates
    return if (counter > 10000000) -1 else counter.toInt()
}

// Inspiration: https://rafal.io/posts/codility-intersecting-discs.html

/**
 * We draw N discs on a plane. The discs are numbered from 0 to N − 1. An array A of N non-negative integers, specifying the radiuses of the discs, is given. The J-th disc is drawn with its center at (J, 0) and radius A[J].
 *
 * We say that the J-th disc and K-th disc intersect if J ≠ K and the J-th and K-th discs have at least one common point (assuming that the discs contain their borders).
 *
 * The figure below shows discs drawn for N = 6 and A as follows:
 *
 *   A[0] = 1
 *   A[1] = 5
 *   A[2] = 2
 *   A[3] = 1
 *   A[4] = 4
 *   A[5] = 0
 *
 *
 * There are eleven (unordered) pairs of discs that intersect, namely:
 *
 * discs 1 and 4 intersect, and both intersect with all the other discs;
 * disc 2 also intersects with discs 0 and 3.
 * Write a function:
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given an array A describing N discs as explained above, returns the number of (unordered) pairs of intersecting discs. The function should return −1 if the number of intersecting pairs exceeds 10,000,000.
 *
 * Given array A shown above, the function should return 11, as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [0..100,000];
 * each element of array A is an integer within the range [0..2,147,483,647].
 */