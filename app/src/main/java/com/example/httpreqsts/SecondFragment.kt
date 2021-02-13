package com.example.httpreqsts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.android.volley.toolbox.Volley
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import org.json.JSONArray
import org.json.JSONObject


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        makeCall(view)
    }

    fun makeCall(view: View) {
        val textView = view.findViewById<TextView>(R.id.textView)
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(getActivity())
        val url = "http://54.179.124.22/api/json"

// Request a string response from the provided URL.
        val stringRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener {response ->
                    var displayResponse = "Response is: "
                    val testArray = response.getJSONArray("array")
                    for (i in 0..(testArray.length()-1)) {
                        val item :String = testArray.get(i) as String
                        displayResponse += "${item}, "
                    }
                    val testHello = response.getString("hello")
                    displayResponse += testHello
                    textView.text = "Response is: ${displayResponse}"
            }, Response.ErrorListener { error ->
                textView.text = "That didn't work! Error was:${error} "
        })

// Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}