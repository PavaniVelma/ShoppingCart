package com.apolisb42.shoppingcart.view.orderslist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apolisb42.shoppingcart.databinding.ItemOrdersBinding
import com.apolisb42.shoppingcart.model.orderdetails.Order

class OrdersAdapter(private val ordersList :List<Order>):
    RecyclerView.Adapter<OrdersAdapter.OrderViewHOlder>()
{
    inner class OrderViewHOlder(val binding: ItemOrdersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(order: Order) {
            with(binding) {
                orderId.text = "OrderId# ${order.order_id}"
                billAmount.text ="BillAmount: $ ${ order.bill_amount}"
                paymentMethod.text = "PaymentMethod: ${order.payment_method}"
                orderStatus.text = "OrderStatus: ${order.order_status}"
                orderDate.text ="OrderDate:${order.order_date}"
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHOlder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemOrdersBinding.inflate(layoutInflater, parent, false)
        return OrderViewHOlder(binding)
    }

    override fun getItemCount() = ordersList.size

    override fun onBindViewHolder(holder: OrderViewHOlder, position: Int) {
        holder.bind(ordersList[position])
    }
}
