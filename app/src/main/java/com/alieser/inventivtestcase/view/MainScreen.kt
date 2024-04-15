package com.alieser.inventivtestcase.view

import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alieser.inventivtestcase.R
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.hilt.navigation.compose.hiltViewModel
import com.alieser.inventivtestcase.CardRefreshTimeManager
import com.alieser.inventivtestcase.Resource
import com.alieser.inventivtestcase.helper.Util.toMasked
import com.alieser.inventivtestcase.helper.Util.toSplit
import com.alieser.inventivtestcase.entity.WalletItemResponse
import com.alieser.inventivtestcase.viewmodel.MainScreenViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.coroutines.launch

@Composable
fun MainScreen(mainScreenViewModel : MainScreenViewModel = hiltViewModel()) {
    val response = mainScreenViewModel.response.observeAsState().value
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(colorResource(id = R.color.topBarColor)),
        ) {
            ElevatedButton(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .border(0.dp, Color.Transparent, RectangleShape),
                onClick = {

                },
                shape = RectangleShape,
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = colorResource(id = R.color.topBarColor),
                    contentColor = Color.White
                )
            ) {
                Icon(painter = painterResource(id = R.drawable.wallet_icon), contentDescription = "")
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = stringResource(id = R.string.walletText), color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            ElevatedButton(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .border(0.dp, Color.Transparent, RectangleShape),
                onClick = {

                },
                shape = RectangleShape,
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = colorResource(id = R.color.topBarColor),
                    contentColor = Color.White
                )
            ) {
                Icon(painter = painterResource(id = R.drawable.market_icon), contentDescription = "")
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = stringResource(id = R.string.salePointsText), color = Color.White,fontSize = 18.sp, fontWeight = FontWeight.Bold)

            }
        }
        if (response != null) {
            when(response) {
                is Resource.Success -> {
                    if (response.data != null) {
                        SwipeCardScreen(walletList = response.data)
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(context,"Something went wrong",Toast.LENGTH_LONG).show()
                }
                is Resource.Loading -> {
                    CircularProgressIndicator()
                }
            }
        }

        Column(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ElevatedButton(
                onClick = {

                },
                shape = RectangleShape,
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = Color.White,
                    contentColor = colorResource(id = R.color.statusBarColor)
                )
            ) {
                Icon(painter = painterResource(id = R.drawable.map_icon), contentDescription = "")
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = stringResource(id = R.string.whereDoText), color = Color.Black)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                onClick = {

                },
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = colorResource(id = R.color.topBarColor)
                )
            ) {
                Text(text = stringResource(id = R.string.payText))
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(bottom = 20.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center)
            {
                Text(text = stringResource(id = R.string.topUpText), modifier = Modifier
                    .weight(1f)
                    .clickable { }, textAlign = TextAlign.Center)
                Text(text = stringResource(id = R.string.manageText), modifier = Modifier
                    .weight(1f)
                    .clickable { }, textAlign = TextAlign.Center)
            }
        }
    }
}
@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun SwipeCardScreen(walletList : List<WalletItemResponse>,mainScreenViewModel : MainScreenViewModel = hiltViewModel()) {

    val clipboardManager : ClipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    var indexState by remember {
        mutableIntStateOf(0)
    }
    var cardNumberState by remember {
        mutableStateOf("")
    }
    var cvvNumberState by remember {
        mutableStateOf("")
    }
    var isVisible  by remember {
        mutableStateOf(false)
    }
    var isRefresh  by remember {
        mutableStateOf(false)
    }
    var refreshTimeText by remember {
        mutableStateOf("")
    }
    val pageCount = walletList.size
    val pagerState = rememberPagerState() {
        pageCount
    }

    if (isVisible) {
        cardNumberState = walletList[indexState].number.toSplit(4)
        cvvNumberState = walletList[indexState].cvv
    } else {
        cardNumberState = walletList[indexState].number.toMasked(4, chunkSize = 4)
        cvvNumberState = walletList[indexState].cvv.toMasked(0)
    }

    if (isRefresh) {
        /* Refresh butonuna tıklandığında kart numarasına göre sorgulama olmadığından,api dan tüm veriler
        tekrar güncellemektedir. */

        mainScreenViewModel.getWallets()
        isRefresh = false
        refreshTimeText = CardRefreshTimeManager.resetCardRefreshTime(walletList[indexState].number)
    } else {
        refreshTimeText = CardRefreshTimeManager.setOrGetLastProcessTimeAgo(walletList[indexState].number)
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            indexState = page
            isVisible = CardRefreshTimeManager.changeAndGetCardInfo(walletList[indexState].number)
        }
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp)
        .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top)
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(start = 24.dp, end = 24.dp),
            contentAlignment = Alignment.BottomStart
        )
        {
            HorizontalPager(
                state = pagerState,
                pageSize = PageSize.Fill,
                modifier = Modifier
                    .fillMaxSize()
            ) {index ->
                GlideImage(model = walletList[index].image, contentDescription = "", contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize())
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 10.dp, top = 10.dp, end = 30.dp),
                horizontalAlignment = Alignment.End

            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text(text = (cardNumberState), modifier = Modifier.wrapContentSize())
                    CustomCopyIcon(R.drawable.copy_icon) {
                        clipboardManager.setText(AnnotatedString(walletList[indexState].number))
                        Toast.makeText(context,"Card number copied",Toast.LENGTH_LONG).show()
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,

                ) {
                    Text(text = stringResource(id = R.string.cvvText))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = cvvNumberState)
                    Spacer(modifier = Modifier.width(6.dp))
                    CustomVisibleIcon(isVisible) {
                        isVisible = CardRefreshTimeManager.changeAndGetCardInfo(walletList[indexState].number,true)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        PageIndicator(pageCount = pageCount, currentPage = pagerState.currentPage)
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.balanceText), color = Color.Black)
            Row(modifier = Modifier
                .wrapContentWidth()
                .height(50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center)
            {
                Text(text = refreshTimeText)
                IconButton(onClick = { isRefresh = true })
                {
                    Icon(
                        painter = painterResource(id = R.drawable.refresh_icon),
                        contentDescription = "")
                }

            }
            Text(text = walletList[indexState].balance.toString(), fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(id = R.string.pendingBalanceText),color = Color.Black)
                Text(text = walletList[indexState].pendingBalance.toString())
            }
        }
    }
}
@Composable
fun PageIndicator(pageCount : Int,currentPage : Int) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ){
        repeat(pageCount) {
            IndicatorDots(isSelected = it == currentPage)
        }
    }
}

@Composable
fun IndicatorDots(isSelected : Boolean) {
    val size = animateDpAsState(targetValue = 8.dp, label = "")
    Box(modifier = Modifier
        .padding(2.dp)
        .size(size.value)
        .clip(CircleShape)
        .background(if (isSelected) Color.DarkGray else Color.LightGray)
    )
}

@Composable
fun CustomVisibleIcon(isVisible : Boolean,onClick : () -> Unit) {

    var iconId  by remember {
        mutableIntStateOf(R.drawable.invisible_icon)
    }
    if (isVisible) {
        iconId = R.drawable.visible_icon
    }
    else {
        iconId = R.drawable.invisible_icon
    }
    Column(modifier = Modifier
        .size(25.dp)
        .border(1.dp, Color.Black, shape = RectangleShape)
        .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = {
                onClick()
            }) {
            Icon(painter = painterResource(id = iconId),"")
        }
    }
}

@Composable
fun CustomCopyIcon(iconId : Int,onClick: () -> Unit) {
    Column(modifier = Modifier
        .size(25.dp)
        .border(1.dp, Color.Black, shape = RectangleShape)
        .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = {
                onClick()
            }) {
            Icon(painter = painterResource(id = iconId),"")
        }

    }
}