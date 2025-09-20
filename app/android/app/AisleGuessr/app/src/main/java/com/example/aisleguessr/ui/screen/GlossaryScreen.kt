package com.example.aisleguessr.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aisleguessr.R
import com.example.aisleguessr.ui.components.BackButton
import com.example.aisleguessr.ui.theme.AisleGuessrTheme
import com.example.aisleguessr.viewmodel.GlossaryViewModel
import com.example.aisleguessr.data.GlossaryEntry
import com.example.aisleguessr.dimensions.Dimensions
import com.example.aisleguessr.ui.components.AppTextField

@Composable
fun GlossaryScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
){
    val aisles = stringArrayResource(id = R.array.glossary_aisle_numbers)
    val contents = stringArrayResource(id = R.array.glossary_contents)

    val allGlossaryEntries = remember(aisles, contents) {
        aisles.mapIndexed { index ,aisle ->
            GlossaryEntry(aisle = aisle, content = contents[index])
        }
    }

    val viewModel: GlossaryViewModel = viewModel(
        factory = GlossaryViewModel.provideFactory(allGlossaryEntries)
    )

    val searchQuery by viewModel.searchQuery.collectAsState()
    val filteredEntries by viewModel.filteredEntries.collectAsState()

    val bodyMediumSpanStyle = MaterialTheme.typography.bodyMedium.toSpanStyle()
    val highlightColor = MaterialTheme.colorScheme.tertiary

    val aisleNumberStyle = bodyMediumSpanStyle.copy(fontWeight = FontWeight.Bold)
    val highlightStyle = SpanStyle(color = Color.Black, background = highlightColor)

    val annotatedText by remember(searchQuery, filteredEntries) {
        derivedStateOf {
            getFilteredAndHighlightedText(
                searchQuery = searchQuery,
                entries = filteredEntries,
                aisleNumberStyle = aisleNumberStyle,
                highlightStyle = highlightStyle
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimensions.SCREEN_MARGIN, end = Dimensions.SCREEN_MARGIN, top = Dimensions.SCREEN_MARGIN),
            contentAlignment = Alignment.Center
        ) {
            BackButton(
                labelResId = R.string.back_arrow,
                onClick = onBackClicked,
                modifier = Modifier.align(Alignment.CenterStart),
                contentDescriptionResId = R.string.back_arrow
            )
            Text(
                text = stringResource(id = R.string.glossary),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        AppTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimensions.SCREEN_MARGIN, end = Dimensions.SCREEN_MARGIN, top = Dimensions.SCREEN_MARGIN),
            leadingIcon = { Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search_icon"
            ) },
            labelResId = R.string.search_for_a_product,
            contentDescriptionResId = R.string.glossary_search_bar,
            value = searchQuery,
            onValueChange = viewModel::onSearchQueryChange,
            isEnabled = true,
            maxLengthOfTextField = 20,
            keyboardType = KeyboardOptions.Default
        )

        Text(
            text = annotatedText,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(start = Dimensions.SCREEN_MARGIN, end = Dimensions.SCREEN_MARGIN, top = Dimensions.SCREEN_MARGIN, bottom = Dimensions.SCREEN_MARGIN)
                .verticalScroll(rememberScrollState())
        )
    }
}

private fun getFilteredAndHighlightedText(
    searchQuery: String,
    entries: List<GlossaryEntry>,
    aisleNumberStyle: SpanStyle,
    highlightStyle: SpanStyle
): AnnotatedString {

    return buildAnnotatedString {
        entries.forEachIndexed { index, entry ->
            withStyle(style = aisleNumberStyle){
                append(entry.aisle)
            }
            append(": ")

            val contentToMatch = entry.content
            val regex = Regex(searchQuery, RegexOption.IGNORE_CASE)
            var lastIndex = 0

            regex.findAll(contentToMatch).forEach { matchResult ->
                append(contentToMatch.substring(lastIndex, matchResult.range.first))
                withStyle(style = highlightStyle) {
                    append(matchResult.value)
                }
                lastIndex = matchResult.range.last + 1
            }
            append(contentToMatch.substring(lastIndex, contentToMatch.length))
            if (index < entries.size - 1) {
                append("\n\n")
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun GlossaryScreenPreviewLight(){
    AisleGuessrTheme {
        GlossaryScreen(onBackClicked = {})
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GlossaryScreenPreviewDark(){
    AisleGuessrTheme {
        GlossaryScreen(onBackClicked = {})
    }
}