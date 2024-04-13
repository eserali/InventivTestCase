package com.alieser.inventivtestcase.view

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alieser.inventivtestcase.Resource
import com.alieser.inventivtestcase.Util.toMasked
import com.alieser.inventivtestcase.entity.WalletItemResponse
import com.alieser.inventivtestcase.viewmodel.MainScreenViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun MainScreen(mainScreenViewModel : MainScreenViewModel = viewModel()) {
    //val mainScreenViewModel : MainScreenViewModel = viewModel()
    val response = mainScreenViewModel.response.observeAsState().value

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
                Text(text = "WALLET", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
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
                Text(text = "SALE POINTS", color = Color.White,fontSize = 18.sp, fontWeight = FontWeight.Bold)

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
                    // Toast message
                }
                is Resource.Loading -> {
                    CircularProgressIndicator()
                }
            }
        }

        //Spacer(modifier = Modifier.height(50.dp))
        

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
                Text(text = "WHERE DO I USE?", color = Color.Black)
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
                Text(text = "PAY")
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(bottom = 20.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center)
            {
                Text(text = "TOP UP", modifier = Modifier
                    .weight(1f)
                    .clickable { }, textAlign = TextAlign.Center)
                Text(text = "MANAGE", modifier = Modifier
                    .weight(1f)
                    .clickable { }, textAlign = TextAlign.Center)
            }
        }
    }
}
@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun SwipeCardScreen(walletList : List<WalletItemResponse>,mainScreenViewModel : MainScreenViewModel = viewModel()) {
    var indexState by remember {
        mutableIntStateOf(0)
    }
    val pageCount = walletList.size
    val pagerState = rememberPagerState {
        pageCount
    }
    val refreshTime = mainScreenViewModel.refreshTime.observeAsState().value

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 30.dp, end = 30.dp)
        .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            contentAlignment = Alignment.BottomStart
        )
        {
            HorizontalPager(
                state = pagerState,
                pageSize = PageSize.Fill,
                modifier = Modifier
                    .fillMaxSize()
            ) {index ->
                indexState = index
                GlideImage(model = walletList[index].image, contentDescription = "", contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize())

            }

            Row (
                modifier = Modifier.padding(start = 16.dp,bottom = 40.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {

                Text(text = (walletList[indexState].number.toMasked(4, chunkSize = 4)))
                Spacer(modifier = Modifier.width(80.dp))
                CustomIcon(R.drawable.copy_icon)
                Spacer(modifier = Modifier.height(30.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 16.dp, bottom = 20.dp, end = 75.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(text = "CVV ${walletList[indexState].cvv.toMasked(0)}")
                Spacer(modifier = Modifier.width(10.dp))
                CustomIcon(R.drawable.detail_icon)
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        PageIndicator(pageCount = pageCount, currentPage = pagerState.currentPage)
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "BALANCE", color = Color.Black)
            Row(modifier = Modifier
                .wrapContentWidth()
                .height(50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Text(text = refreshTime!!)
                IconButton(onClick = {

                }) {
                    Icon(painter = painterResource(id = R.drawable.refresh_icon), contentDescription = "", modifier = Modifier.clickable {
                        mainScreenViewModel.getWallet()
                    })
                }
            }
            Text(text = walletList[indexState].balance.toString(), fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "PENDING BALANCE")
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
    val size = animateDpAsState(targetValue = 8.dp)
    Box(modifier = Modifier
        .padding(2.dp)
        .size(size.value)
        .clip(CircleShape)
        .background(if (isSelected) Color.DarkGray else Color.LightGray)
    )
}

fun changeBalanceValue(count : String) : String {

    val number = count.toDoubleOrNull() ?: return count
    return String.format("%.2f", number / 100).replace(",", ".")
}

@Composable
fun CustomIcon(iconId : Int) {
    Column(modifier = Modifier
        .size(20.dp)
        .border(1.dp, Color.Black, shape = RectangleShape)
        .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(painter = painterResource(id = iconId),"")
    }
}