<template>
	<view class="page">
		<cu-custom v-show="isBack" bgColor="bg-blue" :isBack="isBack">
			<block slot="content" style="width: calc(100% - 100px);">2019九室爱岗敬业选举</block>
		</cu-custom>
		<view class="vote-wrapper padding-xl">
			<view class="vote-tt" style="font-size: 18px;text-align: center;line-height: 3;border-bottom: 1px solid #eee;">
				2019九室爱岗敬业选举统计结果
			</view>
			<view class="vote-tt" style="margin-top: 40upx;">
				投票期限：<text class="vote-subtt">2019-01-02 至 2019-02-01</text>
			</view>

			<view class="">
				<view class="ques-item">
					<view class="ques-tt">
						1.下面的几个人中，你觉得谁最应该获得此荣誉？
					</view>
					<view class="">
						<view class="ques-td">
							<text class="opt-name">lucy（50票）</text>
							<view class="cu-progress round sm striped">
								<view class="bg-red" :style="[{ width:percent? percent:''}]">{{percent}} </view>
							</view>
						</view>
						<view class="ques-td">
							<text class="opt-name">王小二（50票）</text>
							<view class="cu-progress round sm striped">
								<view class="bg-green" :style="[{ width:percent? percent:''}]">{{percent}}</view>
							</view>
						</view>
						<view class="ques-td">
							<text class="opt-name">易烊千玺（500票）</text>
							<view class="cu-progress round sm striped">
								<view class="bg-blue" :style="[{ width:percent? percent:''}]">{{percent}}</view>
							</view>
						</view>
					</view>
				</view>
				<view class="ques-item">
					<view class="ques-tt">
						2.下面的几个人中，你觉得谁最爱岗敬业？
					</view>
					<view class="">
						<view class="ques-td">
							<text class="opt-name">麦当劳（50票）</text>
							<view class="cu-progress round sm striped">
								<view class="bg-red" :style="[{ width:percent? percent:''}]">{{percent}} </view>
							</view>
						</view>
						<view class="ques-td">
							<text class="opt-name">肯德基（50票）</text>
							<view class="cu-progress round sm striped">
								<view class="bg-green" :style="[{ width:percent? percent:''}]">{{percent}}</view>
							</view>
						</view>
						<view class="ques-td">
							<text class="opt-name">必胜客（500票）</text>
							<view class="cu-progress round sm striped">
								<view class="bg-blue" :style="[{ width:percent? percent:''}]">{{percent}}</view>
							</view>
						</view>
					</view>
				</view>
				<view class="ques-item">
					<view class="ques-tt">
						3.你觉得爱岗敬业的庆祝活动应该在哪里举行呢， 为什么呢？
					</view>
					<view class="">
						<view class="ques-td">
							<text class="opt-name"> 【Lucy】我觉得巴拉巴拉巴拉巴拉。然后沙和尚啥啥啥啥啥的。</text>
						</view>
						<view class="ques-td">
							<text class="opt-name"> 【黄中国】我觉得巴拉巴拉巴拉巴拉。然后沙和尚啥啥啥啥啥的。</text>
						</view>
						<view class="ques-td">
							<text class="opt-name"> 【黄中国】巴黎。浪漫。</text>
						</view>
					</view>
				</view>
			</view>

		</view>

		<view style="margin-top: 50upx;text-align: center;">
			<button class="cu-btn bg-blue shadow-blur">分享投票统计结果</button>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				isBack: false,
				percent: '48%',
				colorList: [
					'#e54d42',
					'#f37b1d',
					'#fbbd08',
					'#8dc63f',
					'#39b54a',
					'#1cbbb4',
					'#0081ff',
					'#6739b6',
					'#9c26b0',
					'#e03997',
					'#a5673f',
					'#8799a3',
					'#aaaaaa',
					'#333333',
					'#ffffff'
				]
			};
		},
		onLoad() {
			if (getCurrentPages().length > 1) {
				this.isBack = true;
			}
		}
	}
</script>

<style lang="scss">
	.ques-item {
		margin: 40upx 0 20upx;

		.ques-tt {
			line-height: 2;
		}

		.ques-td {
			display: flex;
			font-size: 12px;
			padding: 0upx 0 10upx;
			flex-direction: column;
			justify-content: flex-start;
			align-items: flex-start;

			.opt-name {
				line-height: 2;
			}
		}
	}
</style>
