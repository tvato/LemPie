package eu.tvato.lempie.utils

import android.content.Context
import android.text.Spanned
import android.text.method.BaseMovementMethod
import android.text.util.Linkify
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.noties.markwon.Markwon
import io.noties.markwon.core.CorePlugin
import io.noties.markwon.core.spans.BlockQuoteSpan
import io.noties.markwon.core.spans.BulletListItemSpan
import io.noties.markwon.core.spans.CodeBlockSpan
import io.noties.markwon.core.spans.CodeSpan
import io.noties.markwon.core.spans.CustomTypefaceSpan
import io.noties.markwon.core.spans.EmphasisSpan
import io.noties.markwon.core.spans.HeadingSpan
import io.noties.markwon.core.spans.LastLineSpacingSpan
import io.noties.markwon.core.spans.LinkSpan
import io.noties.markwon.core.spans.OrderedListItemSpan
import io.noties.markwon.core.spans.StrongEmphasisSpan
import io.noties.markwon.core.spans.TextLayoutSpan
import io.noties.markwon.core.spans.TextViewSpan
import io.noties.markwon.core.spans.ThematicBreakSpan
import io.noties.markwon.ext.strikethrough.StrikethroughPlugin
import io.noties.markwon.ext.tables.TableAwareMovementMethod
import io.noties.markwon.ext.tables.TablePlugin
import io.noties.markwon.html.HtmlPlugin
import io.noties.markwon.image.coil.CoilImagesPlugin
import io.noties.markwon.linkify.LinkifyPlugin
import io.noties.markwon.movement.MovementMethodPlugin
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object Utils{
    fun parseIsoDate(
        isoString: String?,
        format: String = "MMM d, yy, HH:mm"
    ): String {
        if(isoString == null) return ""
        return Instant.parse(isoString)
            .atZone(ZoneId.systemDefault())
            .format(DateTimeFormatter.ofPattern(format))
    }

    fun parseMarkdown(str: String, context: Context): AnnotatedString {
        val markwon = Markwon.builder(context)
            .usePlugin(CorePlugin.create())
            .usePlugin(LinkifyPlugin.create(Linkify.WEB_URLS))
            .usePlugin(StrikethroughPlugin.create())
            .usePlugin(TablePlugin.create(context))
            .usePlugin(CoilImagesPlugin.create(context))
            .usePlugin(HtmlPlugin.create())
            .usePlugin(MovementMethodPlugin.create(TableAwareMovementMethod(BaseMovementMethod())))
            .build()

        val parsed = markwon.parse(str)
        val rendered = markwon.render(parsed)

        return spannedToAnnotated(rendered)
    }

    fun spannedToAnnotated(spanned: Spanned): AnnotatedString {
        val builder = AnnotatedString.Builder()
        builder.append(spanned)

        spanned.getSpans(0, spanned.length, Any::class.java).forEach { span ->
            val start = spanned.getSpanStart(span)
            val end = spanned.getSpanEnd(span)
            when(span){
                is BlockQuoteSpan       -> {    // TODO()
                    /*
                    val level = spanned.getSpans(start, start, BlockQuoteSpan::class.java).size
                    builder.append("Span level is: $level")
                    builder.addStyle(ParagraphStyle(textIndent = TextIndent(firstLine = 16.sp, restLine = 16.sp),
                        lineHeight = TextUnit(22f, TextUnitType.Sp)), start, end)
                    builder.addStyle(SpanStyle(background = Color.DarkGray), start, end)
                    */
                }
                is BulletListItemSpan   -> { /* TODO() */ }
                is CodeBlockSpan        -> { /* TODO() */ }
                is CodeSpan             -> { /* TODO() */ }
                is EmphasisSpan         -> { builder.addStyle(style = SpanStyle(fontStyle = FontStyle.Italic), start, end) }
                is HeadingSpan          -> { builder.addStyle(SpanStyle(fontSize = (30 - (span.level * 2)).sp), start, end) }
                is LinkSpan             -> { builder.addLink(LinkAnnotation.Url(url = span.url), start, end) }
                is OrderedListItemSpan  -> { /* TODO() */ }
                is StrongEmphasisSpan   -> { builder.addStyle(style = SpanStyle(fontWeight = FontWeight.Bold), start, end) }
                is ThematicBreakSpan    -> { /* TODO() */ }

                is TextLayoutSpan       -> { /* Can be ignored */ }
                is TextViewSpan         -> { /* Can be ignored */ }
                is LastLineSpacingSpan  -> { /* Can be ignored */ }
                is CustomTypefaceSpan   -> { /* Can be ignored */ }

                else -> {}
            }
        }

        return builder.toAnnotatedString()
    }

    fun addNumberDelimiter(number: Int?, separator: Char = ','): String {
        if(number == null) return number.toString()
        var outputStr = ""
        number.toString().reversed().forEachIndexed { i, c ->
            if(i % 3 == 0 && i != 0){
                outputStr += separator
                outputStr += c
            }else {
                outputStr += c
            }
        }

        return outputStr.reversed()
    }
}