import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import androidx.fragment.app.Fragment
import com.example.mycookbook.R

class AddRecipeFragment : Fragment() {

    private lateinit var recipeNameEditText: EditText
    private lateinit var ingredientsEditText: EditText
    private lateinit var instructionsEditText: EditText
    private lateinit var ratingBar: RatingBar
    private lateinit var saveButton: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_recipe, container, false)
        recipeNameEditText = view.findViewById(R.id.recipeNameEditText)
        ingredientsEditText = view.findViewById(R.id.ingredientsEditText)
        instructionsEditText = view.findViewById(R.id.instructionsEditText)
        ratingBar = view.findViewById(R.id.ratingBar)
        saveButton = view.findViewById(R.id.saveButton)
        sharedPreferences = requireActivity().getSharedPreferences("MyCookbook", Context.MODE_PRIVATE)

        saveButton.setOnClickListener {
            saveRecipe()
        }

        return view
    }

    private fun saveRecipe() {
        val recipeName = recipeNameEditText.text.toString()
        val ingredients = ingredientsEditText.text.toString()
        val instructions = instructionsEditText.text.toString()
        val rating = ratingBar.rating


        val editor = sharedPreferences.edit()
        val recipeCount = sharedPreferences.getInt("recipe_count", 0)
        editor.putString("recipe_${recipeCount}_name", recipeName)
        editor.putString("recipe_${recipeCount}_ingredients", ingredients)
        editor.putString("recipe_${recipeCount}_instructions", instructions)
        editor.putFloat("recipe_${recipeCount}_rating", rating)
        editor.putInt("recipe_count", recipeCount + 1)
        editor.apply()


        parentFragmentManager.popBackStack()
    }
}
