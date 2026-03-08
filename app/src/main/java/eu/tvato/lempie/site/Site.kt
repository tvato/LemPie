package eu.tvato.lempie.site

import com.google.gson.annotations.SerializedName
import eu.tvato.lempie.customemoji.CustomEmojis
import eu.tvato.lempie.misc.TagLine
import eu.tvato.lempie.user.MyUserInfo
import eu.tvato.lempie.user.User
import eu.tvato.lempie.user.UserCounts

data class Site(
    @SerializedName("id")                   val id: Int,
    @SerializedName("name")                 val name: String,
    @SerializedName("sidebar")              val sidebar: String?,
    @SerializedName("published")            val published: String,
    @SerializedName("updated")              val updated: String?,
    @SerializedName("icon")                 val iconUrl: String?,
    @SerializedName("banner")               val bannerUrl: String?,
    @SerializedName("description")          val description: String?,
    @SerializedName("actor_id")             val actorId: String,
    @SerializedName("last_refreshed_at")    val lastRefreshed: String,
    @SerializedName("inbox_url")            val inboxUrl: String,
    @SerializedName("public_key")           val publicKey: String,
    @SerializedName("instance_id")          val instanceId: Int,
    @SerializedName("content_warning")      val contentWarning: String?
)

data class LocalSite(
    @SerializedName("id")                               val id: Int,
    @SerializedName("site_id")                          val siteId: Int,
    @SerializedName("site_setup")                       val siteSetup: Boolean,
    @SerializedName("enable_downvotes")                 val enableDownvotes: Boolean,
    @SerializedName("enable_nsfw")                      val enableNsfw: Boolean,
    @SerializedName("community_creation_admin_only")    val communityCreationAdminOnly: Boolean,
    @SerializedName("require_email_verification")       val requireEmailVerification: Boolean,
    @SerializedName("application_question")             val appQuestion: String?,
    @SerializedName("private_instance")                 val privateInstance: Boolean,
    @SerializedName("default_theme")                    val defaultTheme: String,
    @SerializedName("default_post_listing_type")        val defaultPostListingType: String,
    @SerializedName("legal_information")                val legalInformation: String?,
    @SerializedName("hide_modlog_mod_names")            val hideModlogModNames: Boolean,
    @SerializedName("application_email_admins")         val appEmailAdmins: Boolean,
    @SerializedName("slur_filter_regex")                val slurFilterRegex: String?,
    @SerializedName("actor_name_max_length")            val actorNameMaxLength: Int,
    @SerializedName("federation_enabled")               val federationEnabled: Boolean,
    @SerializedName("captcha_enabled")                  val captchaEnabled: Boolean,
    @SerializedName("captcha_difficulty")               val captchaDifficulty: String,
    @SerializedName("published")                        val published: String,
    @SerializedName("updated")                          val updated: String?,
    @SerializedName("registration_mode")                val registrationMode: String,
    @SerializedName("reports_email_admins")             val reportsEmailAdmins: Boolean,
    @SerializedName("federation_signed_fetch")          val federationSignedFetch: Boolean,
    @SerializedName("default_post_listing_mode")        val defaultPostListingMode: String,
    @SerializedName("default_sort_type")                val defaultSortType: String
)

data class LocalSiteRateLimit(
    @SerializedName("local_site_id")                    val localSiteId: Int,
    @SerializedName("message")                          val message: Int,
    @SerializedName("message_per_second")               val messagePerSecond: Int,
    @SerializedName("post")                             val post: Int,
    @SerializedName("post_per_second")                  val postPerSecond: Int,
    @SerializedName("register")                         val register: Int,
    @SerializedName("register_per_second")              val registerPerSecond: Int,
    @SerializedName("image")                            val image: Int,
    @SerializedName("image_per_second")                 val imagePerSecond: Int,
    @SerializedName("comment")                          val comment: Int,
    @SerializedName("comment_per_second")               val commentPerSecond: Int,
    @SerializedName("search")                           val search: Int,
    @SerializedName("search_per_second")                val searchPerSecond: Int,
    @SerializedName("published")                        val published: String,
    @SerializedName("updated")                          val updated: String?,
    @SerializedName("import_user_settings")             val importUserSettings: Int,
    @SerializedName("import_user_settings_per_second")  val importUserSettingsPerSecond: Int
)

data class SiteCounts(
    @SerializedName("site_id")                  val siteId: Int,
    @SerializedName("users")                    val users: Int,
    @SerializedName("posts")                    val posts: Int,
    @SerializedName("comments")                 val comments: Int,
    @SerializedName("communities")              val communities: Int,
    @SerializedName("users_active_day")         val usersActiveDay: Int,
    @SerializedName("users_active_week")        val usersActiveWeek: Int,
    @SerializedName("users_active_month")       val usersActiveMonth: Int,
    @SerializedName("users_active_half_year")   val userActiveHalfYear: Int
)

data class SiteView(
    @SerializedName("site")                     val site: Site,
    @SerializedName("local_site")               val localSite: LocalSite,
    @SerializedName("local_site_rate_limit")    val localSiteRateLimit: LocalSiteRateLimit,
    @SerializedName("counts")                   val counts: SiteCounts
)

data class AllLanguages(        // TODO() Move this to somewhere
    @SerializedName("id")   val id: Int,
    @SerializedName("code") val code: String,
    @SerializedName("name") val name: String
)

data class AdminView(      // TODO() Move this to somewhere
    @SerializedName("person")   val user: User,
    @SerializedName("counts")   val counts: UserCounts,
    @SerializedName("is_admin") val isAdmin: Boolean
)

data class BlockedUrl(      // TODO() Move this to somewhere
    @SerializedName("id")           val urlId: Int,
    @SerializedName("url")          val url: String,
    @SerializedName("published")    val published: String,
    @SerializedName("updated")      val updated: String?
)

data class SiteResponse(
    @SerializedName("site_view")            val siteView: SiteView,
    @SerializedName("admins")               val admins: List<AdminView>,
    @SerializedName("version")              val version: String,
    @SerializedName("my_user")              val myUser: MyUserInfo?,
    @SerializedName("all_languages")        val allLanguages: List<AllLanguages>,
    @SerializedName("discussion_language")  val languages: List<Int>,
    @SerializedName("taglines")             val taglines: List<TagLine>,
    @SerializedName("custom_emojis")        val customEmojis: List<CustomEmojis>,
    @SerializedName("blocked_urls")         val blockedUrls: List<BlockedUrl>
)