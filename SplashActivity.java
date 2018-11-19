
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        scheduleSplashScreen()
    }
    private fun scheduleSplashScreen() {
        val splashScreenDuration = getSplashScreenDuration()
        Handler().postDelayed(
                {
                    // After the splash screen duration, route to the right activities
                    val user = UserDb.getCurrentUser()
                    routeToAppropriatePage(user)
                    finish()
                },
                splashScreenDuration
        )
    }
    private fun getSplashScreenDuration() = 2000L
    private fun routeToAppropriatePage(user: User) {
        // Example routing
        when {
            user == null -> OnboardingActivity.start(this)
            user.hasPhoneNumber() -> EditProfileActivity.start(this)
            user.hasSubscriptionExpired() -> PaymentPlansActivity.start(this)
            else -> HomeActivity.start(this)
        }
    }
}
