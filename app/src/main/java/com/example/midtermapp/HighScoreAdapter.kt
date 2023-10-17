
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.midtermapp.Score
import com.example.midtermapp.ScoreDiffItemCallback
import com.example.midtermapp.databinding.RvLayoutBinding
class HighScoreAdapter (val deleteClickListener: (noteId: Long) -> Unit)
    : ListAdapter<Score, HighScoreAdapter.ScoreItemViewHolder>(ScoreDiffItemCallback()) {
    /**
     * The HighScoresAdapter  allows us to communicate between two interfaces that are not compatible,
     * in our case this is the view and the database by binding them to communicate.
     * This allows us to access the commands to the database in the ModelView.
     *
     * @param deleteClickListener listening for the action of the delete button being pressed
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ScoreItemViewHolder = ScoreItemViewHolder.inflateFrom(parent)
    override fun onBindViewHolder(holder: ScoreItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, deleteClickListener)
    }

    class ScoreItemViewHolder(val binding: RvLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {

        companion object {
            // inflates the values from the ScoreItem's viewholder
            fun inflateFrom(parent: ViewGroup): ScoreItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RvLayoutBinding.inflate(layoutInflater, parent, false)
                return ScoreItemViewHolder(binding)
            }
        }

        // binds the values together
        fun bind(item: Score, deleteClickListener: (noteId: Long) -> Unit) {
            binding.score = item
            binding.xButton.setOnClickListener{ item.scoreId?.let { it1 ->
                deleteClickListener(
                    it1
                )
            } }
        }
    }
}