import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.swengineering.everyportal.navigation.home.RankingFragment

class HomeViewpagerAdapter(fragmentManager: FragmentManager, life: Lifecycle) :
    FragmentStateAdapter(fragmentManager, life) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RankingFragment()
            else -> RankingFragment()
        }
    }
}