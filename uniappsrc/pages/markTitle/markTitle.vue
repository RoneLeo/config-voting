<template>
	<view class="page">
		<login></login>
		<cu-custom v-show="isBack" bgColor="bg-blue" :isBack="isBack">
			<block slot="content" style="width: calc(100% - 100px);">科研组专家打分活动</block>
		</cu-custom>

		<view class="vote-wrapper padding">
			<view class="vote-tt title" style="font-size: 18px;">
				2019科研组专家打分活动
			</view>
			<view class="vote-tt" style="margin-top: 40upx;">
				考评对象：<text class="vote-subtt">Lucy</text>
			</view>
			<view class="vote-tt">
				考评说明：<text class="vote-subtt">投票说明投票说明投票说明投票说明投票说明</text>
			</view>

			<view class="padding">
				<view class="mark-item">
					<text class="mark-name">考评内容</text>
					<text class="mark-status">打分区间</text>
					<text class="mark-input">
						打分
					</text>
				</view>
				<view class="mark-item" v-for="(item, index) in data" :key="index">
					<text class="mark-name">{{item.name}}</text>
					<view class="mark-status">
						<text class="mark-num">{{item.min}}</text>
						<view class="mark-range">
							<Range :min='rangeValues[index][0]' :width="180" :max='rangeValues[index][1]' v-model='item.val' :data-index="index"
							 @rangechange='onRangeChange'></Range>
						</view>
						<text class="mark-num">{{item.max}}</text>
					</view>
					<text class="mark-input">
						{{scores[index]}}
					</text>
				</view>

			</view>

			<view style="margin-top: 50upx;text-align: center;">
				<button class="cu-btn bg-blue shadow-blur" @tap="saveRes">保存打分结果</button>
			</view>

		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				isBack: false,
				rangeValues: [], //当前区间值
				slideMin: 30, //slider最小值
				slideMax: 60, //slider最大值
				scores: [],
				data: [{
						name: '专业能力专业能力专业能力',
						min: 0,
						max: 100,
					},
					{
						name: '协调能力',
						min: 10,
						max: 100
					},
					{
						name: '管理能力',
						min: 40,
						max: 100
					},
					{
						name: '应变能力',
						min: 60,
						max: 100
					}
				]
			};
		},
		mounted() {

		},
		onLoad() {
			if (getCurrentPages().length > 1) {
				this.isBack = true;
			}
			this.rangeValues = [];
			this.scores = [];
			this.data.forEach(item => {
				this.rangeValues.push([item.min, item.max]);
				item.val = item.max;
				this.scores.push(item.max)
			})
		},
		methods: {
			
			saveRes() {
				let res = this.data.map(item => {
					return item.val
				})
				console.log(res)
			},
			onRangeChange: function(e) {
				this.scores = this.data.map(item => {
					return item.val;
				})
			},
			gotoMarkTitle() {
				uni.navigateTo({
					url: '../../pages/markTitle/markTitle'
				})
			}

		}
	}
</script>

<style lang="scss">
	.vote-wrapper {
		padding: 20px 30upx;

		.vote-tt {
			font-size: 14px;
			color: #333;
			line-height: 2;

			.vote-subtt {
				color: #666;
			}
		}
	}

	.mark-item {
		display: flex;
		flex-wrap: nowrap;
		justify-content: space-around;
		margin-bottom: 20upx;
		align-items: center;

		.mark-name,
		.mark-input,
		.mark-status {
			text-align: center;
			// flex: 1;
		}

		.mark-name {
			width: 230upx;
		}

		.mark-status {
			width: 320upx;
			display: flex;
			justify-content: space-around;
			align-items: center;

			.mark-num {
				width: 50upx;
			}

			.mark-range {
				width: 180upx;
			}
		}

		.mark-input {
			width: 130upx;
		}
	}
</style>
