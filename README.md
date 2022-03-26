# SimpleRecyclerViewApp

Shows how to display some items in a simple list using the `RecyclerView` and `RecycleView.Adapter`.

The `RecycleView` is the entity that displays the list to the user on the layout of your Ui. </br>
The `RecyclerView.Adapter<T>` is the entity that adapts and manipulates to the recycler to be displayed on the user interface later when
rendering the android view, it also updates the data with their respective views. </br>

## Prepare your Ui : 
- Create a MainActivity class :
```kt
package com.example.session2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
```
- Define the activity inside the `AndroidManifest.xml` :
```xml
<activity
  android:name=".MainActivity"
  android:exported="true">
</activity>
```
- Define an Action main intent filter with a launcher category (build a launcher activity when the user clicks the app icon in the 
app drawer) :
```xml
<activity
  android:name=".MainActivity"
  android:exported="true">
    <intent-filter>
      <action android:name="android.intent.action.MAIN" />

      <category android:name="android.intent.category.LAUNCHER" />
  </intent-filter>
</activity>
```
- Define the binding framework from the jetpack compose (shortcut to accessing views with their IDs directly and their root easily) :
**In app/gradle.build android block :**
```groovy
android {
    ...
    buildFeatures {
        viewBinding true
    }
}
```
- Naviagte to `app/src/main/res/layout/activity_main.xml` and add the context to the root layout : 
```xml
tools:context=".MainActivity"
```
- Add a recyclerview to your xml file with an id.
- The final `activity_main.xml` file should look like that :
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recycler"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:listitem="@layout/item_model" />
</LinearLayout>
```
- Navigate to the `onCreate(onSavedInstance: Bundle)` method, and set your xml file using `setContentView()` and the inflated layout : 
```kotlin
binding = ActivityMainBinding.inflate(layoutInflater)
setContentView(binding.root)
```
- Now a `RecyclerView` instance is predefined and the ready to be accessed using its binding reference which returns a recycler view instance.
- Now setup your recycler `LayoutManager` and `Adapter`.
- `LayoutManager` : controls how the items are getting viewed to the user (Linear or Grid).
- `RecyclerView.Adapter` : holds the data to be adapted later on the user interface.
- The `RecyclerView.Adapter` accepts an array of any type as a parameter -- the array is the data that would be adapted onto the ui later when
instantiating the android view.
- The `LayoutManager` can be a `LinearLayoutManager` for displaying items underneath each other, or `GridLayoutManager` for displaying items in 
a grid fashion.
- The final setup so far : 
```kt
class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "First App"
        
        val array = ArrayList<ItemModel>()
        array.add(ItemModel(R.drawable.ic_launcher_background, "Ahmed"))
        array.add(ItemModel(R.drawable.ic_launcher_background, "Abdo"))
        array.add(ItemModel(R.drawable.ic_launcher_background, "Eman"))
        
        val adapter = Adapter(array)
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter
    }
}
```
## Bind data to the Ui : 
- Create a DataModel class and this class would hold the data for each position to be rendered on its respective android views : 
```kt
data class ItemModel(val photo: Int, val name: String)
```
- Create a `RecyclerView.ViewHolder` that holds the definition of each children views on each position with the recycler list :
```kt
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(binding: ItemModelBinding) : RecyclerView.ViewHolder(binding.root) {
        val photo = binding.image
        val name = binding.textView
}
```

- Create a `RecyclerView.Adapter` that would return an instance of a ViewHolder and adapters data onto the children views on each position : 
```kt
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.session2.databinding.ItemModelBinding

class Adapter(private val list: ArrayList<ItemModel>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = ItemModelBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(inflatedView, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.name.text = item.name
        holder.photo.setOnClickListener {
            Toast.makeText(it.context, position.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
```
