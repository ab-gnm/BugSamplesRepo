package com.example.flowtestapplication.textmeasure

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

@OptIn(ExperimentalComposeUiApi::class)
@Preview(fontScale = 1.0f, name = "1.0 font scale")
@Preview(fontScale = 1.3f, name = "1.3 font scale")
@Preview(fontScale = 1.5f, name = "1.5 font scale")
@Preview(fontScale = 2.0f, name = "2.0 font scale")
@Composable
fun TextMeasureSample(
    modifier: Modifier = Modifier,
) {
    val tag = "Tag"
    val description = LoremIpsum(5).values
        .joinToString()
        .replace("\n", "")

//    DisableNonLinearFontScalingInCompose = true

    val measurer = rememberTextMeasurer()

    val style = MaterialTheme.typography.bodyMedium

    // Calculate text width for given style
    val size = measurer.measure(
        text = tag,
        style = style,
    ).size

    // Convert width to SP so we can pass it to inline content placeholder
    val width = with(LocalDensity.current) { size.width.toSp() }
    var actualWidth by remember { mutableIntStateOf(0) }

    val inlineContent = "tag" to InlineTextContent(
        placeholder = Placeholder(
            width = width,
            height = style.lineHeight,
            placeholderVerticalAlign = PlaceholderVerticalAlign.Center,
        ),
        children = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.inversePrimary)
            ) {
                Text(
                    text = tag,
                    style = style,
                    onTextLayout = {
                        actualWidth = it.size.width
                    }
                )
            }
        }
    )

    Text(
        inlineContent = mapOf(inlineContent),
        text = buildAnnotatedString {
            appendInlineContent("tag")
            withStyle(
                style.toSpanStyle()
            ) {
                append(description)
            }
        },
        modifier = modifier,
    )
}