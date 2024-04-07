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
import androidx.compose.runtime.remember
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
import androidx.compose.foundation.pager.rememberPagerState
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun MainScreen() {
    val imageList = listOf<String>(
        "https://dummyimage.com/450x300/5db185/18163d.png&text=INVENTIV",
        "https://dummyimage.com/450x300/5db185/18163d.png&text=INVENTIV-2"
    )
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

        ImageSlider(imageList = imageList)
        //Spacer(modifier = Modifier.height(50.dp))
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
                Text(text = "0 seconds ago")
                IconButton(onClick = {

                }) {
                    Icon(painter = painterResource(id = R.drawable.refresh_icon), contentDescription = "")
                }
            }
            Text(text = changeBalanceValue("100050"), fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "PENDING BALANCE")
                Text(text = "₺500.00")
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
fun ImageSlider(imageList : List<String>) {
    var indexState by remember {
        mutableIntStateOf(0)
    }
    val pageCount = imageList.size
    val pagerState = rememberPagerState {
        pageCount
    }
    val cardNumberList = listOf<String>(
        "1234567812345678",
        "1234123412341234"
    )
    val cvvNumberList = listOf<String>(
        "123",
        "321"
    )
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 30.dp, end = 30.dp)
        .height(250.dp),
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
                GlideImage(model = imageList[index], contentDescription = "", contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize())
            }
            Row (
                modifier = Modifier.padding(start = 16.dp,bottom = 40.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(text = maskCardNumber(cardNumberList[indexState]))
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
                Text(text = "CVV ***")
                Spacer(modifier = Modifier.width(10.dp))
                CustomIcon(R.drawable.detail_icon)
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        PageIndicator(pageCount = pageCount, currentPage = pagerState.currentPage)
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

fun maskCardNumber(cardNumber : String) : String {
    // gelen stringi trim ve length kontrolü yapayım mı sor
    val maskCardNumberText = StringBuilder()

    for (i in cardNumber.indices) {
        if (i > 0 && i % 4 == 0) {
            maskCardNumberText.append(' ')
        }
        if (i < cardNumber.length - 4) {
            maskCardNumberText.append('*')
        } else {
            maskCardNumberText.append(cardNumber[i])
        }
    }
    return  maskCardNumberText.toString()
}
fun changeBalanceValue(count : String) : String {
    /*
    for (i in count.length - 1 downTo 0 ) {
        if(count[i].isDigit()) {

        }
    }*/
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