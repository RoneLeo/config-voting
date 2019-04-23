<template>
	<view class="page">
		<login></login>
		<cu-custom v-show="isBack" bgColor="bg-blue" :isBack="isBack">
			<block slot="content" style="width: calc(100% - 100px);">2019九室爱岗敬业选举</block>
		</cu-custom>

		<view class="vote-wrapper padding">
			<view class="vote-tt title" style="font-size: 18px;">
				2019九室爱岗敬业选举
			</view>
			<view class="vote-tt" style="margin-top: 40upx;">
				投票期限：<text class="vote-subtt">2019-01-02 至 2019-02-01</text>
			</view>
			<view class="vote-tt">
				投票说明：<text class="vote-subtt">投票说明投票说明投票说明投票说明投票说明</text>
			</view>

			<view class="padding">
				<scroll-view class="scroll-wrapper" scroll-x="false" :scroll-into-view="'item' + current">
					<view id="item1" class="scroll-view-item">
						<view class="item-block">
							<view class="item-tt">
								<view class='cu-tag radius bg-blue' style="margin-right: 20upx;">单选</view>
								你认为下面谁最应该获得最佳奖项？
							</view>

							<view class="item-td padding-sm">
								<radio-group @change="radioChange" style="width: 100%;">
									<label v-for="(item, index) in 4" :key="item">
										<radio :value="''+item" :checked="index === 1" />
										<view class="label-text">
											某某{{item}}某某{{item}}某某{{item}}某某{{item}}某某{{item}}某某{{item}}某某{{item}}某某{{item}}某某{{item}}
											<view class="detail">详情</view>
										</view>
									</label>
								</radio-group>
							</view>
						</view>

					</view>

					<view id="item2" class="scroll-view-item">
						<view class="item-block">
							<view class="item-tt">
								<view class='cu-tag radius bg-blue' style="margin-right: 20upx;">多选</view>
								你认为下面谁最应该获得最佳奖项？
							</view>

							<view class="item-td padding-sm">
								<checkbox-group ref="checkbox1" @change="checkboxChange(1)">
									<label v-for="(item, index) in 5" :key="item">
										<checkbox :value="''+item" />
										<view class="label-text">多选项{{item}}多选项{{item}}</view>
									</label>
								</checkbox-group>
							</view>
						</view>
					</view>

					<view id="item3" class="scroll-view-item">
						<view class="item-block">
							<view class="item-tt">
								<view class='cu-tag radius bg-blue' style="margin-right: 20upx;">不定项</view>
								你认为下面谁最应该获得最佳奖项？
							</view>
							<view class="item-td padding-sm">
								<checkbox-group ref="checkbox2" @change="checkboxChange(2)">
									<label v-for="(item, index) in checkboxItems" :key="item.value">
										<view>
											<checkbox :value="item.value" />
										</view>
										<view class="label-text">{{item.name}}</view>
									</label>
								</checkbox-group>
							</view>
						</view>
					</view>

				</scroll-view>
				<view class="pages padding justify-center">
					<button class="cu-btn bg-blue" @tap="lastOne">上一题</button>
					<text style="width: 100upx;text-align: center;display: inline-block;align-self: center;">{{current}} / 3</text>
					<button class="cu-btn bg-blue" @tap="nextOne">下一题</button>
				</view>

			</view>

			<view>
				<!-- <el-checkbox :options="printer.operate" v-model="printer.selected" max="2" @change="select" style="margin-top:-10px;"></el-checkbox> -->
			</view>

			<!-- <view class="vote-tt" style="display: flex;justify-content: flex-start;">
				<view>投票注解：</view>
				<view class="vote-subtt">
					<view>
						单选：投票人选择的选项数等于1。
					</view>
					<view>
						多选：投票人选择的选项大于等于2。
					</view>
					<view>
						不定项：投票人选择的选项大于等于1。
					</view>
				</view>
			</view> -->
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				isBack: false,
				current: 1,
				slider: null,
				selected: false,
				checked: true,
				printer: {
					name: '',
					operate: [{
							label: '下单打印',
							id: 1,
						},
						{
							label: '付款打印',
							id: 2
						},
						{
							label: '确认收货打印',
							id: 3
						}
					],
					selected: [1, 3]
				},
				checkboxItems: [{
						value: 'USA',
						name: '美国',
						checked: true
					},
					{
						value: 'USA1',
						name: '美国1',
						checked: false
					},
					{
						value: 'USA2',
						name: '美国2',
						checked: false
					}
				]
			};
		},
		mounted() {
			//#ifdef H5
			// 			var input = document.createElement('input')
			// 			input.type = 'file'
			// 			input.setAttribute('id', 'fileInput');
			// 			input.onchange = (event) => {
			// 				console.log(event.target.files[0])
			// 			}
			// 			this.$refs.input1.$el.appendChild(input)
			//#endif
		},
		onLoad() {
			if (getCurrentPages().length > 1) {
				this.isBack = true;
			}
		},
		methods: {
			select(data) {
				console.log(data);
			},
			checkOptionChange(e) {
				console.log('checkOptionChange', e);
			},

			radioChange(e) {
				console.log('radioChange', e)
			},
			checkboxChange(index) {
				let ref = 'checkbox' + index;
				let checkboxs = this.$refs[ref].checkboxList.length && this.$refs[ref].checkboxList;
				checkboxs.forEach((item, index) => {
					// this.checkboxItems[index].checked = item.checkboxChecked;
					if (item.checkboxChecked) {
						console.log(item.checkboxValue)
					}
				})
				console.log(this.checkboxItems)
			},

			lastOne() {
				if (this.current > 1) {
					this.current--;
				}
				// 				var num = this.currentItem.replace(/[^0-9]/ig, "");
				// 				this.currentItem = 'item' + (parseInt(num) - 1)
			},
			nextOne() {
				if (this.current < 3) {
					this.current++;
				}
				// 				var num = this.currentItem.replace(/[^0-9]/ig, "");
				// 
				// 				this.currentItem = 'item' + (parseInt(num) + 1)
				// 				console.log(this.currentItem)
			},

			gotoVoteTitle() {
				uni.navigateTo({
					url: '../../pages/voteTitle/voteTitle'
				})
			},
			SwitchB(e) {
				this.switchB = e.detail.value
			},
			startDateChange(e) {
				this.startDate = e.detail.value
			},
			endDateChange(e) {
				this.endDate = e.detail.value
			},
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

	.scroll-wrapper {
		width: 100%;
		white-space: nowrap;
		// display: flex;
		// height: 400px;
		// justify-content: flex-start;

		.scroll-view-item {
			width: 100%;
			height: auto;
			display: inline-block;

			.item-block {
				display: flex;
				flex-wrap: wrap;
				justify-content: flex-start;
				align-items: flex-start;

				.item-td label {
					padding: 10upx;
					display: flex;
					justify-content: flex-start;
					align-items: flex-start;
					width: 100%;
					white-space: normal;

					.label-text {
						padding-left: 20upx;
						display: inline-block;
						width: 550upx;

					}
				}

				.item-td {
					width: 100%;

					.detail {
						border: 1px solid #eee;
						color: #888;
						border-radius: 10upx;
						padding: 0upx 10upx;
						text-align: center;
						display: inline-block;
						font-size: 12px;
						float: right;
						margin-right: 20upx;
					}
				}
			}
		}
	}

	.pages {
		display: flex;
	}
</style>
