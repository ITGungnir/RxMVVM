package my.itgungnir.rxmvvm.app5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Description:
 *
 * Created by ITGungnir on 2019-12-07
 */
class AppListAdapter5(private var dataList: List<String>) : RecyclerView.Adapter<AppListAdapter5.VH>() {

    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.text1.text = dataList[position]
    }

    fun refresh(newList: List<String>) {
        this.dataList = newList
        notifyDataSetChanged()
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text1 = itemView as TextView
    }
}