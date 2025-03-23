import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mycookbook.R

class RecipeDetailsFragment : Fragment() {

    private lateinit var recipeNameTextView: TextView
    private lateinit var ingredientsTextView: TextView
    private lateinit var instructionsTextView: TextView
    private lateinit var recipeRatingBar: RatingBar
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_details, container, false)
        recipeNameTextView = view.findViewById(R.id.recipeNameTextView)
        ingredientsTextView = view.findViewById(R.id.ingredientsTextView)
        instructionsTextView = view.findViewById(R.id.instructionsTextView)
        recipeRatingBar = view.findViewById(R.id.recipeRatingBar)
        sharedPreferences = requireActivity().getSharedPreferences("MyCookbook", Context.MODE_PRIVATE)

        loadRecipeDetails()

        return view
    }

    private fun loadRecipeDetails() {
        val recipeName = sharedPreferences.getString("recipe_0_name", "Unknown")
        val ingredients = sharedPreferences.getString("recipe_0_ingredients", "Unknown")
        val instructions = sharedPreferences.getString("recipe_0_instructions", "Unknown")
        val rating = sharedPreferences.getFloat("recipe_0_rating", 0f)

        recipeNameTextView.text = recipeName
        ingredientsTextView.text = ingredients
        instructionsTextView.text = instructions
        recipeRatingBar.rating = rating
    }
}
