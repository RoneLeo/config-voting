<template>
	<view class="page">
		<login></login>
		<cu-custom v-show="isBack" bgColor="bg-blue" :isBack="isBack">
			<block slot="content" style="width: calc(100% - 100px);">2019专业组技术带头人打分</block>
		</cu-custom>
		<view class="vote-wrapper padding">
			<view class="vote-tt title" style="font-size: 18px;">
				2019专业组技术带头人打分统计结果
			</view>
			<view class="vote-tt" style="margin-top: 40upx;">
				评分期限：<text class="vote-subtt">2019-01-02 至 2019-02-01</text>
			</view>
			<!-- <view class="margin-top">
				<view class="count-item bg-green light " style="color: #333; border-radius: 20upx;padding: 20upx;display:block;line-height: 2;">
					<view style="font-size: 14px;">
						NO.1 易烊千玺 总分：100
					</view>
					<view class="detail" style="font-size: 12px;">
						<view>管理能力：100分 排名( 1 )</view>
						<view>领导能力加应变能力加临场发挥：100分 NO.1</view>
					</view>
				</view>
			</view> -->
			
			<view class="margin-top">
				<view class="count-item bg-blue light" style="height: 80upx;">
					<view class="count-item-index">排名</view>
					<view class="count-item-name">对象姓名</view>
					<view class="count-item-score">总分</view>
					<view class="count-item-detail" style="text-align: center;">得分详情</view>
				</view>
				<view class="count-item bg-blue light">
					<view class="count-item-index">1</view>
					<view class="count-item-name">易烊千玺</view>
					<view class="count-item-score">100</view>
					<view class="count-item-detail">
						<view>管理能力：100分 排名：1</view>
						<view>领导能力加应变能力加临场发挥：100分 排名：1</view>
					</view>
				</view>
				<view class="count-item bg-blue light">
					<view class="count-item-index">2</view>
					<view class="count-item-name">王源</view>
					<view class="count-item-score">100</view>
					<view class="count-item-detail">
						<view>管理能力：100分 排名：1</view>
						<view>领导能力加应变能力加临场发挥：100分 排名：1</view>
						<view>领导能力加应变能力加临场发挥把卡啊：100分 排名：1</view>
						<view>领导能力加应变能力加临场发挥：100分 排名：1</view>
					</view>
				</view>
				<view class="count-item bg-blue light">
					<view class="count-item-index">3</view>
					<view class="count-item-name">王俊凯</view>
					<view class="count-item-score">100</view>
					<view class="count-item-detail">
						<view>管理能力：100分 NO.1</view>
						<view>领导能力加应变能力加临场发挥：100分 NO.1</view>
					</view>
				</view>
				<view class="count-item bg-blue light">
					<view class="count-item-index">44</view>
					<view class="count-item-name">王俊凯</view>
					<view class="count-item-score">100</view>
					<view class="count-item-detail">
						<view>管理能力：100分 NO.1</view>
						<view>领导能力加应变能力加临场发挥：100分 NO.1</view>
					</view>
				</view>
			</view>

		</view>
		
		<view style="margin-top: 50upx;text-align: center;">
			<button class="cu-btn bg-blue shadow-blur">分享打分统计结果</button>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				isBack: false,
				
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
	.count-item {
		padding: 20upx 0;
		display: flex;
		justify-content: space-between;
		align-items: center;
		color: #333;
		font-size: 12px;
		border-bottom: 1px solid #eee;
		.count-item-index, .count-item-name, .count-item-score {
			text-align: center;
			height: auto;
		}
		
		.count-item-index {
			width: 70upx;
		}
		.count-item-name {
			width: 140upx;
		}
		.count-item-score {
			width: 80upx;
		}
		.count-item-detail {
			width: 390upx;
			padding-left: 10upx;
			
		}
	}
</style>
