# detekt

## Metrics

* 14 number of properties

* 17 number of functions

* 9 number of classes

* 6 number of packages

* 12 number of kt files

## Complexity Report

* 494 lines of code (loc)

* 441 source lines of code (sloc)

* 255 logical lines of code (lloc)

* 0 comment lines of code (cloc)

* 18 cyclomatic complexity (mcc)

* 0 cognitive complexity

* 21 number of total code smells

* 0% comment source ratio

* 70 mcc per 1,000 lloc

* 82 code smells per 1,000 lloc

## Findings (21)

### formatting, NoUnusedImports (1)

Detects unused imports

[Documentation](https://detekt.dev/docs/rules/formatting#nounusedimports)

* D:/Prgramowanie/Projekty android studio/Warehouse/app/src/main/java/com/example/warehouse/MainActivity.kt:6:1
```
Unused import
```
```kotlin
3  import android.os.Bundle
4  import androidx.activity.ComponentActivity
5  import androidx.activity.compose.setContent
6  import androidx.compose.ui.res.stringResource
!  ^ error
7  import com.example.navigation.navGraphMain
8  import com.example.resources.theme.WarehouseTheme
9  import dagger.hilt.android.AndroidEntryPoint

```

### naming, FunctionNaming (15)

Function names should follow the naming convention set in the configuration.

[Documentation](https://detekt.dev/docs/rules/naming#functionnaming)

* D:/Prgramowanie/Projekty android studio/Warehouse/feature/home/src/main/java/com/example/component/WHCard.kt:13:14
```
Function names should match the pattern: [a-z][a-zA-Z0-9]*
```
```kotlin
10 import androidx.compose.ui.unit.dp
11 
12 @Composable
13 internal fun WHCard(
!!              ^ error
14     modifier: Modifier = Modifier,
15     onClicked: () -> Unit,
16     content: @Composable () -> Unit,

```

* D:/Prgramowanie/Projekty android studio/Warehouse/feature/home/src/main/java/com/example/component/WHScreenContainer.kt:20:14
```
Function names should match the pattern: [a-z][a-zA-Z0-9]*
```
```kotlin
17 
18 @OptIn(ExperimentalMaterial3Api::class)
19 @Composable
20 internal fun WHScreenContainer(
!!              ^ error
21     title: String,
22     onClicked: () -> Unit,
23     content: @Composable () -> Unit,

```

* D:/Prgramowanie/Projekty android studio/Warehouse/feature/home/src/main/java/com/example/contractors/presentation/ContractorsScreen.kt:28:5
```
Function names should match the pattern: [a-z][a-zA-Z0-9]*
```
```kotlin
25 import com.example.resources.R as ResR
26 
27 @Composable
28 fun ContractorsScreen() {
!!     ^ error
29     val viewModel: ContractorsViewModel = hiltViewModel()
30     val state by viewModel.state.collectAsState()
31 

```

* D:/Prgramowanie/Projekty android studio/Warehouse/feature/home/src/main/java/com/example/contractors/presentation/ContractorsScreen.kt:39:13
```
Function names should match the pattern: [a-z][a-zA-Z0-9]*
```
```kotlin
36 }
37 
38 @Composable
39 private fun ContractorsScreen(
!!             ^ error
40     contractors: List<String>,
41     onAddClicked: () -> Unit,
42 ) {

```

* D:/Prgramowanie/Projekty android studio/Warehouse/feature/home/src/main/java/com/example/contractors/presentation/ContractorsScreen.kt:59:5
```
Function names should match the pattern: [a-z][a-zA-Z0-9]*
```
```kotlin
56 }
57 
58 @Composable
59 fun Item(
!!     ^ error
60     contractor: String,
61     onMoreClicked: () -> Unit,
62 ) {

```

* D:/Prgramowanie/Projekty android studio/Warehouse/feature/home/src/main/java/com/example/contractors/presentation/ContractorsScreen.kt:83:13
```
Function names should match the pattern: [a-z][a-zA-Z0-9]*
```
```kotlin
80 
81 @Composable
82 @Preview
83 private fun ContractorsScreen_Preview() {
!!             ^ error
84     ContractorsScreen(
85         contractors = mutableListOf("Contractor"),
86         onAddClicked = {},

```

* D:/Prgramowanie/Projekty android studio/Warehouse/feature/home/src/main/java/com/example/contractors/presentation/ContractorsScreen.kt:92:13
```
Function names should match the pattern: [a-z][a-zA-Z0-9]*
```
```kotlin
89 
90 @Composable
91 @Preview
92 private fun Item_Preview() {
!!             ^ error
93     Item(
94         contractor = "Contractor",
95         onMoreClicked = {},

```

* D:/Prgramowanie/Projekty android studio/Warehouse/feature/home/src/main/java/com/example/documents/presentation/DocumentsScreen.kt:24:5
```
Function names should match the pattern: [a-z][a-zA-Z0-9]*
```
```kotlin
21 import com.example.resources.R as ResR
22 
23 @Composable
24 fun DocumentsScreen() {
!!     ^ error
25     val viewModel: DocumentsViewModel = hiltViewModel()
26     val state by viewModel.state.collectAsState()
27     DocumentsScreen(state.documents)

```

* D:/Prgramowanie/Projekty android studio/Warehouse/feature/home/src/main/java/com/example/documents/presentation/DocumentsScreen.kt:31:13
```
Function names should match the pattern: [a-z][a-zA-Z0-9]*
```
```kotlin
28 }
29 
30 @Composable
31 private fun DocumentsScreen(documents: List<Document>) {
!!             ^ error
32     WHScreenContainer(
33         title = stringResource(ResR.string.documents_top_bar_title),
34         onClicked = {},

```

* D:/Prgramowanie/Projekty android studio/Warehouse/feature/home/src/main/java/com/example/documents/presentation/DocumentsScreen.kt:45:13
```
Function names should match the pattern: [a-z][a-zA-Z0-9]*
```
```kotlin
42 }
43 
44 @Composable
45 private fun Item(
!!             ^ error
46     document: Document,
47 ) {
48     WHCard(onClicked = {}) {

```

* D:/Prgramowanie/Projekty android studio/Warehouse/feature/home/src/main/java/com/example/documents/presentation/DocumentsScreen.kt:73:13
```
Function names should match the pattern: [a-z][a-zA-Z0-9]*
```
```kotlin
70 
71 @Composable
72 @Preview
73 private fun DocumentsScreen_Preview() {
!!             ^ error
74     DocumentsScreen(
75         mutableListOf(
76             Document(

```

* D:/Prgramowanie/Projekty android studio/Warehouse/feature/home/src/main/java/com/example/documents/presentation/DocumentsScreen.kt:87:13
```
Function names should match the pattern: [a-z][a-zA-Z0-9]*
```
```kotlin
84 
85 @Composable
86 @Preview
87 private fun Item_Preview() {
!!             ^ error
88     Item(
89         Document(
90             date = "12/12/2034",

```

* D:/Prgramowanie/Projekty android studio/Warehouse/feature/home/src/main/java/com/example/home/presentation/HomeScreen.kt:21:5
```
Function names should match the pattern: [a-z][a-zA-Z0-9]*
```
```kotlin
18 private const val WEIGHT_FOR_CARD = 1f
19 
20 @Composable
21 fun HomeScreen(
!!     ^ error
22     onFirstButtonClicked: () -> Unit,
23     onSecondButtonClicked: () -> Unit,
24 ) {

```

* D:/Prgramowanie/Projekty android studio/Warehouse/feature/home/src/main/java/com/example/home/presentation/HomeScreen.kt:47:13
```
Function names should match the pattern: [a-z][a-zA-Z0-9]*
```
```kotlin
44 }
45 
46 @Composable
47 private fun ClickableCard(
!!             ^ error
48     modifier: Modifier,
49     text: String,
50     onClicked: () -> Unit,

```

* D:/Prgramowanie/Projekty android studio/Warehouse/feature/home/src/main/java/com/example/home/presentation/HomeScreen.kt:67:13
```
Function names should match the pattern: [a-z][a-zA-Z0-9]*
```
```kotlin
64 
65 @Composable
66 @Preview
67 private fun HomeScreen_Preview(function: () -> Unit) {
!!             ^ error
68     HomeScreen(
69         onFirstButtonClicked = function,
70         onSecondButtonClicked = function,

```

### style, UnusedPrivateMember (5)

Private function is unused and should be removed.

[Documentation](https://detekt.dev/docs/rules/style#unusedprivatemember)

* D:/Prgramowanie/Projekty android studio/Warehouse/feature/home/src/main/java/com/example/contractors/presentation/ContractorsScreen.kt:83:13
```
Private function `ContractorsScreen_Preview` is unused.
```
```kotlin
80 
81 @Composable
82 @Preview
83 private fun ContractorsScreen_Preview() {
!!             ^ error
84     ContractorsScreen(
85         contractors = mutableListOf("Contractor"),
86         onAddClicked = {},

```

* D:/Prgramowanie/Projekty android studio/Warehouse/feature/home/src/main/java/com/example/contractors/presentation/ContractorsScreen.kt:92:13
```
Private function `Item_Preview` is unused.
```
```kotlin
89 
90 @Composable
91 @Preview
92 private fun Item_Preview() {
!!             ^ error
93     Item(
94         contractor = "Contractor",
95         onMoreClicked = {},

```

* D:/Prgramowanie/Projekty android studio/Warehouse/feature/home/src/main/java/com/example/documents/presentation/DocumentsScreen.kt:73:13
```
Private function `DocumentsScreen_Preview` is unused.
```
```kotlin
70 
71 @Composable
72 @Preview
73 private fun DocumentsScreen_Preview() {
!!             ^ error
74     DocumentsScreen(
75         mutableListOf(
76             Document(

```

* D:/Prgramowanie/Projekty android studio/Warehouse/feature/home/src/main/java/com/example/documents/presentation/DocumentsScreen.kt:87:13
```
Private function `Item_Preview` is unused.
```
```kotlin
84 
85 @Composable
86 @Preview
87 private fun Item_Preview() {
!!             ^ error
88     Item(
89         Document(
90             date = "12/12/2034",

```

* D:/Prgramowanie/Projekty android studio/Warehouse/feature/home/src/main/java/com/example/home/presentation/HomeScreen.kt:67:13
```
Private function `HomeScreen_Preview` is unused.
```
```kotlin
64 
65 @Composable
66 @Preview
67 private fun HomeScreen_Preview(function: () -> Unit) {
!!             ^ error
68     HomeScreen(
69         onFirstButtonClicked = function,
70         onSecondButtonClicked = function,

```

generated with [detekt version 1.23.5](https://detekt.dev/) on 2024-02-13 15:17:09 UTC
