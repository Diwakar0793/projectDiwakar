package com.application.scoo.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.application.scoo.R
import com.application.scoo.databinding.ActivityMenuBinding
import com.application.scoo.login.LoginActivity
import com.application.scoo.ui.Contact
import com.application.scoo.ui.PayRental
import com.application.scoo.ui.Profile
import com.application.scoo.ui.SelectEV
import com.application.scoo.ui.base.BaseActivity
import com.google.android.material.navigation.NavigationView
import dagger.android.AndroidInjection
import javax.inject.Inject


class MenuActivity : BaseActivity(), View.OnClickListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var menuActivityBinding: ActivityMenuBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        menuActivityBinding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(menuActivityBinding.root)
        /*  val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)*/

        val drawerLayout: DrawerLayout = menuActivityBinding.drawerLayout
        val navView: NavigationView = menuActivityBinding.navView
        val appbar = menuActivityBinding.appBarMain
//        val navHostFragmen = menuActivityBinding.appBarMain.contentMain.navHostFragment
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), drawerLayout
        )
        // setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        menuActivityBinding.appBarMain.drawerIcon.setOnClickListener {
            menuActivityBinding.drawerLayout.openDrawer(Gravity.RIGHT)
            //menuActivityBinding.imCloseBtn.visibility = View.VISIBLE

        }
        menuActivityBinding.appBarMain.notification.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
//            menuActivityBinding.drawerLayout.openDrawer(Gravity.RIGHT)
            //menuActivityBinding.imCloseBtn.visibility = View.VISIBLE

        }

        /*menuActivityBinding.imCloseBtn.setOnClickListener{
            menuActivityBinding.drawerLayout.closeDrawer(navView,true)
            menuActivityBinding.imCloseBtn.visibility = View.GONE
        }
*/
//        menuActivityBinding.appBarMain.backIcon.setOnClickListener{
//           // Toast.makeText(this, "test", Toast.LENGTH_SHORT).show()
//            val a = Intent(Intent.ACTION_MAIN)
//            a.addCategory(Intent.CATEGORY_HOME)
//            a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            startActivity(a)
//           // supportFragmentManager.popBackStack()
//        }
        /*initDropdown()

        menuActivityBinding.appBarMain.chooseEvDropdown.onItemClickListener = AdapterView.OnItemClickListener{
                parent,view,position,id->
            val selectedItem = parent.getItemAtPosition(position).toString()
            // Display the clicked item using toast
            Toast.makeText(applicationContext,"Selected : $selectedItem",Toast.LENGTH_SHORT).show()
        }*/

        navView.setNavigationItemSelectedListener {
            it.isCheckable = true
            when(it.itemId){
                R.id.nav_profile -> movetoProfile()
                R.id.nav_pay -> movetoPayment()
                R.id.nav_help -> movetoHelp()
                R.id.nav_ev -> movetoselectEV()
                R.id.nav_notification -> movetoNotification()
                R.id.nav_logout -> movetoSignIn()
            }
            true
        }

    }

    private fun movetoNotification() {
        val intent = Intent(this, NotificationActivity::class.java)
        startActivity(intent)
    }

    private fun movetoselectEV() {
        val intent = Intent(this, SelectEV::class.java)
        startActivity(intent)
    }

    private fun movetoHelp() {
        val intent = Intent(this, Contact::class.java)
        startActivity(intent)
    }

    private fun movetoPayment() {
        val intent = Intent(this, PayRental::class.java)
        startActivity(intent)
    }

    private fun movetoSignIn() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun movetoProfile() {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
    }


    @Suppress("UNREACHABLE_CODE")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
        val id = item.itemId
        if (id == R.id.nav_logout) {
            Toast.makeText(getApplicationContext(), "Logout user!", Toast.LENGTH_LONG).show();
            return true;
        }
    }
//    override fun onContextItemSelected(item: MenuItem): Boolean {
//        return super.onContextItemSelected(item)
//
//    }

//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        return when (item?.itemId) {
//            android.R.id.home -> {
//                drawerLayout.openDrawer(GravityCompat.START)
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
    private fun initDropdown() {
        val type = arrayOf("Bike 1", "Bike 2", "Bike 3")

        val adapter = ArrayAdapter(
            this,
            R.layout.dropdown_menu_popup_item,
            type
        )

        /* val editTextFilledExposedDropdown = menuActivityBinding.appBarMain.chooseEvDropdown
         editTextFilledExposedDropdown.setAdapter(adapter)

         if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
             editTextFilledExposedDropdown.setOnDismissListener { editTextFilledExposedDropdown.clearFocus() }
         }*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
//            R.id.btn_login_user -> {
//                startActivity(
//                    Intent(
//                        this,
//                        LoginActivity::class.java
//                    )
//                )
//            }
        }
    }
}