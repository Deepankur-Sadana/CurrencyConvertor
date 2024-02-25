class Solution {
    var prices = IntArray(1)
    val map = HashMap<String, Int>()
    fun maxProfit(prices: IntArray): Int {
        this.prices = prices
        map.clear()
        val b = rec(0, buy, 0, false)
        val c = rec(0, rest, 0, false)
        return Math.max(b, c)
    }

    val buy = 1
    val sell = -1
    val rest = 0

    private fun rec(
        idx: Int,
        action: Int,
        runningAccount: Int,
        doesHoldStock: Boolean
    ): Int {
        val hash = "$idx,$action"
        if (map[hash] != null) return map[hash]!!

        if (idx == prices.size) {
            return runningAccount
        }
        val newBal: Int = when (action) {
            buy -> {
                runningAccount - prices[idx]
            }

            sell -> {
                runningAccount + prices[idx]
            }
            else -> { //cool
                runningAccount
            }
        }

        val doesHoldAnyStocksAfterThisTxn = when (action) {
            buy -> {
                true
            }
            sell -> {
                false
            }
            else -> {
                doesHoldStock
            }
        }
/*
        val recBuy = rec(idx + 1, buy, newBal, doesHoldAnyStocksAfterThisTxn)
        val recSell = rec(idx + 1, sell, newBal, doesHoldAnyStocksAfterThisTxn)
        val recCool = rec(idx + 1, rest, newBal, doesHoldAnyStocksAfterThisTxn)

        */

        val res = if (action == sell) {
            rec(idx + 1, rest, newBal, doesHoldAnyStocksAfterThisTxn)
        } else if (action == buy) {
            Math.max(rec(idx + 1, sell, newBal, doesHoldAnyStocksAfterThisTxn),
                rec(idx + 1, rest, newBal, doesHoldAnyStocksAfterThisTxn)
            )
        } else { //cool
            val recCool =  rec(idx + 1, rest, newBal, doesHoldAnyStocksAfterThisTxn)
            if (doesHoldStock) {
                Math.max(rec(idx + 1, sell, newBal, doesHoldAnyStocksAfterThisTxn), recCool)
            } else {
                Math.max(rec(idx + 1, buy, newBal, doesHoldAnyStocksAfterThisTxn), recCool)
            }
        }
        map[hash] = res
        return res
    }
}