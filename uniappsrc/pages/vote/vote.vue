<template>
	<view class="page">
		<login></login>
		<cu-custom v-show="isBack" bgColor="bg-blue" :isBack="isBack">
			<block slot="content" style="width: calc(100% - 100px);">{{activity && activity.bt}}</block>
		</cu-custom>

		<view class="vote-wrapper padding">
			<view class="vote-tt title" style="font-size: 18px;">
				{{activity.bt}}
			</view>
			<view class="vote-tt" style="margin-top: 40upx;">
				投票期限：<text class="vote-subtt">{{activity.kssj && activity.kssj.substring(0,11)}} 至 {{activity.kssj && activity.kssj.substring(0,11)}}</text>
			</view>
			<view class="vote-tt">
				投票说明：<text class="vote-subtt">{{activity.nr}}</text>
			</view>

			<view class="padding">
				<scroll-view class="scroll-wrapper" scroll-x="false" :scroll-into-view="'item' + current">
					<view :id="'item' + (index + 1)" v-for="(vote, index) in activity.votelist" class="scroll-view-item">
						<view class="item-block">
							<view class="item-tt">
								<view class='cu-tag radius bg-blue' style="margin-right: 20upx;">{{myJson.vote[vote.tplx]}}</view>
								{{vote.bt}}
							</view>

							<view class="item-td padding-sm">
								<radio-group v-show="vote.tplx == '1'" @change="radioChange" style="width: 100%;">
									<label v-for="(opt, optIndex) in vote.volist" :key="optIndex">
										<radio :value="opt.id+''" />
										<view class="label-text">
											<text v-show="opt.xxlx == 1 || opt.xxlx == 3"> {{opt.xxmc}}</text>
											<view v-show="opt.xxlx == 2">
												<image :src="opt.xxmc" mode="" style="width: 200upx; height: 150upx;"></image>
											</view>
											<view v-show="opt.xxlx == 4">
												<input placeholder="请填写"></input>
											</view>
											<view class="detail">详情</view>
										</view>
									</label>
								</radio-group>
								<checkbox-group v-show="vote.tplx == '2'" ref="checkbox1" @change="checkboxChange(1)">
									<label v-for="(opt, optIndex) in vote.volist" :key="optIndex">
										<checkbox :value="opt.id+''" />
										<view class="label-text">
											<text v-show="opt.xxlx == 1 || opt.xxlx == 3"> {{opt.xxmc}}</text>
											<view v-show="opt.xxlx == 2">
												<image :src="opt.xxmc" mode="" style="width: 200upx; height: 150upx;"></image>
											</view>
											<view v-show="opt.xxlx == 4">
												<input placeholder="请填写"></input>
											</view>
											<view class="detail">详情</view>
										</view>
									</label>
								</checkbox-group>

							</view>
						</view>

					</view>
				</scroll-view>
				<view class="pages padding justify-center">
					<button class="cu-btn bg-blue" @tap="lastOne">上一题</button>
					<text style="width: 100upx;text-align: center;display: inline-block;align-self: center;">{{current}} / {{activity.votelist && activity.votelist.length}}</text>
					<button class="cu-btn bg-blue" @tap="nextOne">下一题</button>
				</view>
			</view>
			
			<view class="padding" style="text-align: center;">
				<button class="cu-btn bg-blue" @tap="createCode" style="margin-top: 60upx;">发布本次活动</button>
			</view>
		</view>
	</view>
</template>

<script>
	import myJson from '../../static/radio.js'
	export default {
		data() {
			return {
				hdid: null,
				user: {},
				myJson: myJson,
				activity: {},
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
		onLoad(params) {
			this.hdid = params.hdid;
			this.user = this.getGlobalUser() || {};
			if (getCurrentPages().length > 1) {
				this.isBack = true;
			}
			if (this.hdid) {
				this.$api.post('/theme/findAllInfoById', {
					id: this.hdid
				}).then(res => {
					if (res.resCode == 200) {
						this.activity = res.data;
					}
				})
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
				if (this.current < this.activity.votelist.length) {
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
