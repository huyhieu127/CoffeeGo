package com.huyhieu.coffee_go.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.huyhieu.coffee_go.ui.common.SpacerVertical
import com.huyhieu.coffee_go.ui.common.shimmerEffect
import com.huyhieu.coffee_go.ui.theme.actionBarSize
import com.huyhieu.coffee_go.ui.theme.cornerSize
import com.huyhieu.coffee_go.ui.theme.edgeSize

@Preview
@Composable
fun BannerShimmerUi() {
    Box(
        modifier = Modifier
            .padding(edgeSize)
            .aspectRatio(4 / 2F)
            .clip(RoundedCornerShape(cornerSize))
            .shimmerEffect()
    )
}

@Preview
@Composable
fun ToolBarShimmerUi() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(actionBarSize)
            .padding(horizontal = edgeSize),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .shimmerEffect(),
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(1f),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.55F)
                    .height(20.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .shimmerEffect()
            )
            SpacerVertical(4.dp)
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.75F)
                    .height(20.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .shimmerEffect()
            )
        }

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .shimmerEffect(),
        )
    }
}