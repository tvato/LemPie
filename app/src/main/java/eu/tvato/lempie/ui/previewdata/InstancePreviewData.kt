package eu.tvato.lempie.ui.previewdata

import eu.tvato.lempie.site.AdminView
import eu.tvato.lempie.site.AllLanguages
import eu.tvato.lempie.site.LocalSite
import eu.tvato.lempie.site.LocalSiteRateLimit
import eu.tvato.lempie.site.Site
import eu.tvato.lempie.site.SiteCounts
import eu.tvato.lempie.site.SiteResponse
import eu.tvato.lempie.site.SiteView

const val previewSidebar1 = "[What is Lemmy.ml](https://lemmy.ml/post/70280)\n\n" +
        "# Rules\n" +
        "1. No bigotry - including racism, sexism, ableism, homophobia, transphobia, or xenophobia. " +
        "[Code of Conduct](https://join-lemmy.org/docs/en/code_of_conduct.html).\n" +
        "1. Be respectful, [especially when disagreeing](https://lemmy.ml/post/1140303). Everyone should feel welcome here.\n" +
        "1. No porn.\n" +
        "1. No Ads / Spamming.\n\n\n" +
        "Feel free to ask questions over in:\n" +
        "- [!lemmy_support](https://lemmy.ml/c/lemmy_support)\n" +
        "- [Matrix Chat](https://matrix.to/#/#lemmy-space:matrix.org)\n" +
        "- [Mastodon @LemmyDev](https://mastodon.social/@LemmyDev)"

const val previewSidebar2 = "![](https://sopuli.xyz/pictrs/image/73789c82-48a1-4485-81a4-28427a176bac.png)\n\n" +
        "# Rules\n" +
        "1. Remember the human! (no harassment, threats, etc.)\n" +
        "2. No racism or other discrimination\n" +
        "3. No Nazis, QAnon, incels or similar whackos and no endorsement of them\n" +
        "4. No porn\n" +
        "5. No ads or spam\n" +
        "6. No content against Finnish law \n" +
        "------------\n" +
        "# Säännöt\n" +
        "1. Muista ihminen! (ei häirintää, uhkailua, jne)\n" +
        "2. Ei rasismia tai muuta syrjintää\n" +
        "3. Ei natseja, QAnonia, inceleitä tai vastaavia hörhöjä eikä heidän tukemistaan\n" +
        "4. Ei pornoa\n" +
        "5. Ei mainoksia tai roskapostia\n" +
        "6. Ei Suomen lain vastaista sisältöä\n" +
        "-----------------------\n\n" +
        "[Matrix Space](https://matrix.to/#%2F%21SJfHjWlTugnKyonyQi%3Amatrix.org%3Fvia=matrix.org)\n\n" +
        "[FAQ / UKK](https://sopuli.xyz/post/13531)\n\n" +
        "Support and meta discussion / Tuki ja metakeskustelu:\n" +
        "[!meta@sopuli.xyz](https://sopuli.xyz/c/meta) \n\n" +
        "[Patreon](https://www.patreon.com/QuentinCallaghan) \n\n" +
        "BTC: bc1qqdfa98jksa4f6t4ya3t255vuzz0t6ffecas860\n\n" +
        "XRP: rfB6a4sPWqfo5EL8m4MUMeQA7dDchoMsom\n\n" +
        "ETH: 0xE5b1F760BA4334bc311695e125861Eb5870018aD\n\n" +
        "----------------\n" +
        "Communities to get started / Yhteisöjä joilla pääsee alkuun: \n\n" +
        "* [!finland@sopuli.xyz](https://sopuli.xyz/c/finland) / [!suomi@sopuli.xyz](https://sopuli.xyz/c/suomi) \n\n" +
        "* [!arkisuomi@sopuli.xyz](https://sopuli.xyz/c/arkisuomi) \n \n" +
        "* [!main@sopuli.xyz](https://sopuli.xyz/c/main) \n\n" +
        "* [!memes@sopuli.xyz](https://sopuli.xyz/c/memes) \n\n" +
        "* [!music@sopuli.xyz](https://sopuli.xyz/c/music)  \n\n" +
        "* [!videos@sopuli.xyz](https://sopuli.xyz/c/videos) \n\n" +
        "* [!test_community@sopuli.xyz](https://sopuli.xyz/c/test_community) \n\n" +
        "* [!technology@beehaw.org](https://beehaw.org/c/technology) \n\n" +
        "* [!gaming@beehaw.org](https://beehaw.org/c/gaming) \n\n" +
        "* [!entertainment@beehaw.org](https://beehaw.org/c/entertainment) \n\n" +
        "* [!privacy@lemmy.ml](https://lemmy.ml/c/privacy) \n\n" +
        "* [!ukraine@sopuli.xyz](https://sopuli.xyz/c/ukraine)\n\n" +
        "* [!maatapitkinmatkustus@sopuli.xyz](https://sopuli.xyz/c/maatapitkinmatkustus) \n\n" +
        "* [!communitypromo@lemmy.ca](https://lemmy.ca/c/communitypromo) \n\n" +
        "-------------------\n\n" +
        "[The Lemmy Guide](https://paper.wf/rynach/the-lemmy-guide)\n\n" +
        "[Lemmy-opas](https://paper.wf/rynach/lemmy-opas)"

const val previewSidebar3 = "# Markdown syntax guide\n" +
        "\n" +
        "## Headers\n" +
        "\n" +
        "# This is a Heading h1\n" +
        "## This is a Heading h2\n" +
        "### This is a Heading h3\n" +
        "#### This is a Heading h4\n" +
        "##### This is a Heading h5\n" +
        "###### This is a Heading h6\n" +
        "\n" +
        "## Emphasis\n" +
        "\n" +
        "*This text will be italic*  \n" +
        "_This will also be italic_\n" +
        "\n" +
        "**This text will be bold**  \n" +
        "__This will also be bold__\n" +
        "\n" +
        "_You **can** combine them_\n" +
        "\n"

const val previewSidebar4 = "## Lists\n" +
        "\n" +
        "### Unordered\n" +
        "\n" +
        "* Item 1\n" +
        "* Item 2\n" +
        "* Item 2a\n" +
        "* Item 2b\n" +
        "    * Item 3a\n" +
        "    * Item 3b\n" +
        "\n" +
        "### Ordered\n" +
        "\n" +
        "1. Item 1\n" +
        "2. Item 2\n" +
        "3. Item 3\n" +
        "    1. Item 3a\n" +
        "    2. Item 3b\n" +
        "\n" +
        "## Images\n" +
        "\n" +
        "![This is an alt text.](/image/Markdown-mark.svg \"This is a sample image.\")\n" +
        "\n" +
        "## Links\n" +
        "\n" +
        "You may be using [Markdown Live Preview](https://markdownlivepreview.com/).\n" +
        "\n"

const val previewSidebar5 = "## Blockquotes\n" +
        "\n" +
        "> Markdown is a lightweight markup language with plain-text-formatting syntax, created in 2004 by John Gruber with Aaron Swartz.\n" +
        ">\n" +
        ">> Markdown is often used to format readme files, for writing messages in online discussion forums, and to create rich text using a plain text editor.\n" +
        "\n" +
        "## Tables\n" +
        "\n" +
        "| Left columns  | Right columns |\n" +
        "| ------------- |:-------------:|\n" +
        "| left foo      | right foo     |\n" +
        "| left bar      | right bar     |\n" +
        "| left baz      | right baz     |\n" +
        "\n" +
        "## Blocks of code\n" +
        "\n" +
        "```\n" +
        "let message = 'Hello world';\n" +
        "alert(message);\n" +
        "```\n" +
        "\n" +
        "## Inline code\n" +
        "\n" +
        "This web site is using `markedjs/marked`.\n"

val siteView = SiteView(
    site = Site(
        id = 1, name = "Test Site", sidebar = previewSidebar3 + previewSidebar4 + previewSidebar5,
        published = "2022-07-09T17:56:21.988295Z", updated = null, iconUrl = null, bannerUrl = null,
        description = null, actorId = "", lastRefreshed = "2022-07-09T17:56:21.988295Z",
        inboxUrl = "", publicKey = "", instanceId = 1, contentWarning = null
    ),
    localSite = LocalSite(
        id = 1, siteId = 1, siteSetup = true, enableDownvotes = true, enableNsfw = true,
        communityCreationAdminOnly = true, requireEmailVerification = true, appQuestion = null,
        privateInstance = true, defaultTheme = "", defaultPostListingType = "All", legalInformation = null,
        hideModlogModNames = true, appEmailAdmins = true, slurFilterRegex = null, actorNameMaxLength = 100,
        federationEnabled = true, captchaEnabled = true, captchaDifficulty = "", published = "2022-07-09T17:56:21.988295Z",
        updated = null, registrationMode = "Open", reportsEmailAdmins = true, federationSignedFetch = true,
        defaultPostListingMode = "List", defaultSortType = "Active"
    ),
    localSiteRateLimit = LocalSiteRateLimit(
        localSiteId = 1, message = 50, messagePerSecond = 50, post = 50, postPerSecond = 50,
        register = 50, registerPerSecond = 50, image = 50, imagePerSecond = 50, comment = 50,
        commentPerSecond = 50, search = 50, searchPerSecond = 50, published = "2022-07-09T17:56:21.988295Z",
        updated = null, importUserSettings = 50, importUserSettingsPerSecond = 50
    ),
    counts = SiteCounts(
        siteId = 1, users = 324, posts = 578, comments = 1299, communities = 86,
        usersActiveDay = 56, usersActiveWeek = 98, usersActiveMonth = 124, userActiveHalfYear = 230
    )
)

val admins = listOf(
    AdminView(user = previewUserViews[0].user, counts = previewUserViews[0].counts, isAdmin = previewUserViews[0].isAdmin),
    AdminView(user = previewUserViews[1].user, counts = previewUserViews[1].counts, isAdmin = previewUserViews[1].isAdmin),
    AdminView(user = previewUserViews[2].user, counts = previewUserViews[2].counts, isAdmin = previewUserViews[2].isAdmin),
)

val siteResponse = SiteResponse(
    siteView = siteView,
    admins = admins,
    version = "0.15.0",
    myUser = null,
    allLanguages = listOf(AllLanguages(id = 1, code = "", name = "English")),
    languages = listOf(1, 2, 3, 4, 5, 6),
    taglines = listOf(),
    customEmojis = listOf(),
    blockedUrls = listOf()
)