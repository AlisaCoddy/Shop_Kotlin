package com.example.myapplicationshop

import Product
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.KeyPosition
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import kotlin.jvm.java


class SecondActivity : AppCompatActivity() {
    private lateinit var lwList: ListView
    private lateinit var rwGrid: RecyclerView
    private lateinit var listAdapter:  ProductAdapter
    private lateinit var gridAdapter: ProductGridAdapter

    private val products = listOf(
        Product(1, "cosplay 1", 33.60, "Костюм Чарли Морнингстар. Красный полиэстер", R.drawable.one),
        Product(2, "cosplay 2 ", 140.5, "Костюм для косплея Люмин из игры Genshin Impact", R.drawable.two),
        Product(3, "cosplay 3", 40.0, "Костюм Шинобу Кочо Клинок Рассекающий Демонов", R.drawable.three),
        Product(4, "cosplay 4 ", 70.5, "Костюм Джуди Хопс Зверополис", R.drawable.four),
        Product(5, "cosplay 5", 120.0, "Костюм Кирилл Чудомирович Genshin Impact", R.drawable.five),
        Product(6, "cosplay 6", 30.0, "Описание 6", R.drawable.six),
        Product(7, "cosplay 7", 80.0, "Описание 7", R.drawable.seven),
        Product(8, "cosplay 8", 110.0, "Описание 8", R.drawable.eight)
    )

    private val originallist = mutableListOf<Product>()
    private val currentlist = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        originallist.addAll(products)
        currentlist.addAll(products)

        val toolbar= findViewById<MaterialToolbar>(R.id.topBar)
        setSupportActionBar(toolbar)

        //1  шаг. Находим оба списка на экране
        lwList = findViewById(R.id.lvCatalog)
        rwGrid = findViewById(R.id.rvCatalogGrid)

        // шаг 2. Находим адаптер для LW
        listAdapter = ProductAdapter(this,currentlist)

        // шаг 3. Находим адаптер для  RW
        gridAdapter = ProductGridAdapter(this,currentlist)

        // шаг 4. Соединяем адаптер и список LW
        lwList.adapter = listAdapter

        // шаг 5. Соединяем адаптер и список RW
        rwGrid.layoutManager = GridLayoutManager(this, 2)
        rwGrid.adapter = gridAdapter
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        val isGridPref = prefs.getBoolean("isGrid", true)

        if (isGridPref == true){
            showGrid()
        } else{
            showlist()
        }


    }

    private fun showlist(){
        lwList.visibility = View.VISIBLE
        rwGrid.visibility = View.GONE
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        prefs.edit( ).putBoolean("isGrid", false). apply()

        }
    private fun showGrid(){
        lwList.visibility = View.GONE
        rwGrid.visibility = View.VISIBLE
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        prefs.edit( ).putBoolean("isGrid", true). apply()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.menu_second, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.sort_default -> {
                currentlist.clear()
                currentlist.addAll(originallist)
                listAdapter.notifyDataSetChanged()
                gridAdapter.notifyDataSetChanged()
                return true
            }
            R.id.sort_asc -> {
                currentlist.sortBy{it.prise }
                listAdapter.notifyDataSetChanged()
                gridAdapter.notifyDataSetChanged()
                return true
            }
            R.id.sort_desc-> {
                currentlist.sortByDescending{it.prise }
                listAdapter.notifyDataSetChanged()
                gridAdapter.notifyDataSetChanged()
                return true
            }
        }

        if (item.itemId == R.id.action_cart){
            startActivity(Intent(this, CartActivity::class.java))
            return true

        }
        if (item.itemId == R.id.action_list){
            showlist()
            return true

        }
        if (item.itemId == R.id.action_grid){
            showGrid()
            return true

        }
        return super.onOptionsItemSelected(item)
    }






//        val container = findViewById<LinearLayout>(R.id.catalog)
//
//        products.forEach { product ->
//            val view = layoutInflater.inflate(R.layout.item_product, container, false)
//
//            view.findViewById<ImageView>(R.id.ivProductImage).setImageResource(product.ImageRes)
//            view.findViewById<TextView>(R.id.tvProductname).text = product.name
//            view.findViewById<TextView>(R.id.tvProductPrice).text = "${product.prise} $"
//
//            view.findViewById<Button>(R.id.btnDetails).setOnClickListener {
//                val intent = Intent(this, DetailActivity::class.java).apply {
//                    putExtra("name", product.name)
//                    putExtra("price", product.prise)
//                    putExtra("ImageRes", product.ImageRes)
//                    putExtra("description", product.descriptor)
//                }
//                startActivity(intent)
//            }
//
//
//            container.addView(view)
//
//        }
    }


class ProductAdapter(
    private val context:android.content.Context,
    private val products: List<Product>
    ) : android.widget.BaseAdapter(){

    override fun getCount() = products.size
    override fun getItem(position: Int) = products[position]
    override fun getItemId(position: Int) = position.toLong()
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view =  convertView ?: LayoutInflater.from(context)
             .inflate(R.layout.item_product, parent , false)
        val product = getItem(position)
//Находим элементы в карточке товара
        val image =  view.findViewById<ImageView>(R.id.ivProductImage)
        val name = view.findViewById<TextView>(R.id.tvProductname)
        val button = view.findViewById<Button>(R.id.btnDetails)
        val prise =   view.findViewById<TextView>(R.id.tvProductPrice)

        image.setImageResource(product.ImageRes)
        name.text = product.name
        prise.text = "${product.prise} $"

        button.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java).apply {
//Нажатие на кнопку
                putExtra("id", product.id)
                putExtra("name", product.name)
                putExtra("price", product.prise)
                putExtra("ImageRes", product.ImageRes)
                putExtra("description", product.descriptor)
                }
                context.startActivity(intent)
        }

        button.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                v.startAnimation(AnimationUtils.loadAnimation(v.context, R.anim.scale_down))
            }

            if (event.action == MotionEvent.ACTION_UP) {
                v.startAnimation(AnimationUtils.loadAnimation(v.context, R.anim.scale_up))
            }

            if (event.action == MotionEvent.ACTION_CANCEL) {
                v.startAnimation(AnimationUtils.loadAnimation(v.context, R.anim.scale_up))
            }
            false
        }

        return view

    }

    }

