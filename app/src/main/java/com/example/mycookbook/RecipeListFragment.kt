import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.mycookbook.R


class RecipeListFragment : Fragment() {

    private lateinit var recipeListView: ListView
    private lateinit var addRecipeButton: Button
    private var recipes = mutableListOf<String>()
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_list, container, false)
        recipeListView = view.findViewById(R.id.recipeListView)
        addRecipeButton = view.findViewById(R.id.addRecipeButton)
        sharedPreferences = requireActivity().getSharedPreferences("MyCookbook", Context.MODE_PRIVATE)

        loadRecipes()

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, recipes)
        recipeListView.adapter = adapter

        recipeListView.setOnItemClickListener { _, _, position, _ ->
            val detailsFragment = RecipeDetailsFragment()
            val transaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_container, detailsFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        addRecipeButton.setOnClickListener {
            val addRecipeFragment = AddRecipeFragment()
            val transaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_container, addRecipeFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return view
    }

    private fun loadRecipes() {
        val recipeCount = sharedPreferences.getInt("recipe_count", 0)
        for (i in 0 until recipeCount) {
            recipes.add(sharedPreferences.getString("recipe_$i", "") ?: "")
        }
    }
}
