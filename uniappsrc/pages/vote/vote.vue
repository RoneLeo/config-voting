<template>
	<view class="page">
		<login></login>
		<cu-custom v-show="isBack" bgColor="bg-blue" :isBack="isBack">
			<block slot="content" style="width: calc(100% - 100px);">{{activity && activity.bt}}</block>
		</cu-custom>

		<view class="vote-wrapper padding">
			<image v-show="activity.sffb == '2'" src="../../static/img/end.png" mode="" style="width: 150upx; height: 150upx;position: absolute; left: 480upx; top: 140upx;"></image>
			<view class="vote-tt title" style="font-size: 18px;">
				{{activity.bt}}<view v-show="activity.tpzt == '1' || saved" class='cu-tag line-orange radius' style="margin-left: 20upx;">已完成投票</view>
			</view>
			<view class="vote-tt" style="margin-top: 40upx;">
				活动状态：<text class="vote-subtt">{{activity.sffb== '0' ? '尚未发布' : (activity.sffb== '1' ? '已发布' : '活动已结束')}}</text>
			</view>
			<view class="vote-tt">
				投票期限：<text class="vote-subtt">{{activity.kssj && activity.kssj.substring(0,11)}} 至 {{activity.kssj && activity.jssj.substring(0,11)}}</text>
			</view>
			<view class="vote-tt">
				投票说明：<text class="vote-subtt">{{activity.nr}}</text>
			</view>

			<view v-show="activity.sffb != '2'" class="scroll-wrapper" style="padding: 30upx 0;">
				<!-- <scroll-view class="scroll-wrapper" scroll-x="false" :scroll-into-view="'item' + current"> -->
					<view class="scroll-view-item" v-show="current == (index + 1)" :id="'item' + (index + 1)" v-for="(vote, index) in activity.votelist" >
						<view class="item-block">
							<view class="item-tt">
								<view class='cu-tag radius bg-blue' style="margin-right: 20upx;">{{myJson.vote[vote.tplx]}}</view>
								{{vote.bt}}
								<text v-show="vote.tplx=='2'">
									（选择{{vote.zxs}}~{{vote.zds}}）
								</text>
							</view>

							<view class="item-td padding-sm">
								<radio-group :ref="'radio' + index" v-show="vote.tplx == '1'" @change="radioChange(index)" style="margin-left: 30upx;width: 90%;">
									<label v-for="(opt, optIndex) in vote.volist" :key="optIndex">
										<radio :value="opt.id+''" />
										<view class="label-text">
											<text v-show="opt.xxlx == 1 || opt.xxlx == 3"> {{opt.xxmc}}</text>
											<view v-show="opt.xxlx == 2">
												<image :src="opt.xxmc" mode="" style="width: 200upx; height: 150upx;"></image>
											</view>
											<view v-show="opt.xxlx == 4" style="border: 1px solid #e1e1e1;border-radius: 10upx;">
												<input :ref="'input'+index" :data-index="index" :data-opid="opt.id" @input="inputChange" placeholder="请填写"
												 style="padding-left: 20upx;"></input>
											</view>
											<view v-show="opt.xxnr!=''&&opt.xxnr!=null" class="detail">详情</view>
										</view>
									</label>
								</radio-group>
								<checkbox-group :ref="'checkbox' + index" v-show="vote.tplx == '2'" @change="checkboxChange(index)" style="margin-left: 30upx;width: 90%;">
									<label v-for="(opt, optIndex) in vote.volist" :key="optIndex">
										<checkbox :value="opt.id+''" />
										<view class="label-text">
											<text v-show="opt.xxlx == 1 || opt.xxlx == 3"> {{opt.xxmc}}</text>
											<view v-show="opt.xxlx == 2">
												<image :src="opt.xxmc" mode="" style="width: 200upx; height: 150upx;"></image>
											</view>
											<view v-show="opt.xxlx == 4" style="border: 1px solid #e1e1e1;border-radius: 10upx;">
												<input :ref="'input'+index" :data-index="index" :data-opid="opt.id" @input="inputChange" placeholder="请填写"
												 style="padding-left: 20upx;"></input>
											</view>
											<view v-show="opt.xxnr!=''&&opt.xxnr!=null" class="detail">详情</view>
										</view>
									</label>
								</checkbox-group>

							</view>
						</view>

					</view>
				<!-- </scroll-view> -->
				<view class="pages padding justify-center">
					<button class="cu-btn bg-blue" @tap="lastOne">上一题</button>
					<text style="width: 130upx;text-align: center;display: inline-block;align-self: center;">
						{{current}} / {{activity.votelist && activity.votelist.length}}</text>
					<button class="cu-btn bg-blue" @tap="nextOne">下一题</button>
				</view>
				<view style="margin-top: 50upx;">
					<view v-show="user.js == '1' && activity.sffb == '0'" class="padding-sm" style="text-align: center;">
						<button class="cu-btn bg-blue" @tap="createCode" style="margin-top: 60upx;">发布本次活动并生成二维码</button>
					</view>
					
					<view v-show="user.js == '1' && activity.sffb == '1' && curTime > strTime &&  curTime < endTime" class="padding-sm" style="text-align: center;">
						<button class="cu-btn bg-blue" @tap="updateFbzt(2)">提前结束活动</button>
					</view>
					<!-- v-show="activity.sffb=='1' && curTime > strTime && curTime < endTime" -->
					<view v-show="activity.tpzt == '0' && activity.sffb=='1' && curTime > strTime && curTime < endTime && !saved" style="text-align: center;">
						<button class="cu-btn bg-blue shadow-blur" @tap="saveRes">保存我的投票</button>
					</view>
					<view v-show="user.js == '1' && activity.sffb == '1'" class="padding-sm" style="text-align: center;">
						<button class="cu-btn bg-blue" @tap="gotoCodePage">查看活动二维码</button>
					</view>
				</view>
			</view>

			<view v-show="activity.sffb == '2'" class="padding" style="margin-top: 50upx;">
				<view class="padding-sm" style="text-align: center;">
					<button class="cu-btn bg-blue" @tap="gotoCountPage">查看活动统计结果</button>
				</view>
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
				curTime: '',
				strTime: '',
				endTime: '',
				res: [],
				inputRes: [],
				saved: false
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
				this.getData();
			}
		},
		onShow() {
			this.user = this.getGlobalUser() || {};
		},
		computed: {
			logined() {
				return this.$store.state.logined
			}
		},
		watch: {
			logined: {
				handler(newVal, oldVal) {
					this.user = this.getGlobalUser() || {};
					if (newVal) {
						this.getData();
					}
				},
				immediate: true
			}
		},
		methods: {
			inputChange(event) {
				let {
					index,
					opid
				} = event.currentTarget.dataset
				let val = event.target.value
				this.inputRes[index] = {
					oid: opid,
					pid: this.user.id,
					qid: this.activity.votelist[index].id,
					bz: val
				}
			},
			createCode() {
				this.$api.post('/theme/updateFbzt', {
					id: this.hdid,
					fbzt: 1
				}).then(res => {
					if (res.resCode == 200) {
						uni.showToast({
							title: '发布成功！',
							icon: 'none'
						})
						this.gotoCodePage();
					} else {
						uni.showToast({
							title: '操作失败！',
							icon: 'none'
						})
					}
				})
			},
			gotoCountPage() {
				uni.navigateTo({
					url: '../../pages/voteCount/voteCount?hdid=' + this.hdid
				})
			},
			gotoCodePage() {
				uni.navigateTo({
					url: '../../pages/code/code?lx=vote&id=' + this.hdid + '&title=' + this.activity.bt +
						'&kssj=' + this.activity.kssj.substring(0, 11) + '&jssj=' + this.activity.jssj.substring(0, 11)
				})
			},
			getData() {
				uni.showLoading({
					title: '加载中'
				});
				this.$api.post('/theme/findAllInfoById', {
					id: this.hdid
				}).then(res => {
					uni.hideLoading()
					if (res.resCode == 200) {
						this.activity = res.data;
						this.res = new Array(this.activity.votelist.length);
						this.inputRes = new Array(this.activity.votelist.length)
						this.curTime = new Date().getTime();
						this.strTime = new Date(Date.parse(this.activity.kssj.replace(/-/g, "/"))).getTime(); // 时间戳
						this.endTime = new Date(Date.parse(this.activity.jssj.replace(/-/g, "/"))).getTime(); // 时间戳
					}
				})
			},
			updateFbzt(zt) {
				this.$api.post('/theme/updateFbzt', {id: this.hdid, fbzt: zt}).then(res => {
					if(res.resCode == 200) {
						uni.showToast({
							title: '操作成功！',
							icon: 'none'
						})
						uni.navigateBack()
					}else {
						uni.showToast({
							title: '操作失败！',
							icon: 'none'
						})
					}
				})
			},
			saveRes() {
				let len = this.activity.votelist.length;
				
				if(this.activity.votelist[len - 1].tplx == '2' ){  //多选题 
				if(this.activity.votelist[len - 1].zxs) {
					if(this.res[len - 1].length >= this.activity.votelist[len - 1].zxs && this.res[len - 1].length <= this.activity.votelist[len - 1].zds) {
						
					}else {
						uni.showToast({
							title: '请确认你的选择是否符合题目要求',
							icon: 'none'
						})
						return ;
					}
				}
					
				}
				let postData = [];
				for (let i = 0, len = this.res.length; i < len; i++) {
					if (this.res[i] && this.res[i].length) {
						for (let j = 0; j < this.res[i].length; j++) {
							if (this.res[i][j] && this.inputRes[i] && this.res[i][j].oid == this.inputRes[i].oid && this.res[i][j].pid ==
								this.inputRes[i].pid && this.res[i][j].qid == this.inputRes[i].qid) {
								postData.push(this.inputRes[i])
							} else {
								postData.push(this.res[i][j])
							}
						}
					}
				}
				this.$api.post('/voting/vote', JSON.stringify(postData)).then(res => {
					if(res.resCode == 200) {
						uni.showToast({
							title:'保存成功！',
							icon: 'none'
						})
						this.saved = true;
						uni.navigateBack()
					}else {
						uni.showToast({
							title:'保存失败！',
							icon: 'none'
						})
					}
				})
			},
			radioChange(index) {
				let ref = 'radio' + index;

				this.res[index] = [];
				let radios = this.$refs[ref][0].radioList.length && this.$refs[ref][0].radioList;
				radios.forEach((item) => {
					if (item.radioChecked) {
						let resItem = {
							pid: this.user.id,
							qid: this.activity.votelist[index].id,
							oid: item.radioValue,
							bz: ''
						}
						this.res[index].push(resItem);
					}
				})
			},
			checkboxChange(index) {
				let ref = 'checkbox' + index;
				this.res[index] = [];
				let checkboxs = this.$refs[ref][0].checkboxList.length && this.$refs[ref][0].checkboxList;
				checkboxs.forEach((item) => {
					if (item.checkboxChecked) {
						let resItem = {
							pid: this.user.id,
							qid: this.activity.votelist[index].id,
							oid: item.checkboxValue,
							bz: ''
						}
						this.res[index].push(resItem);
					}
				})
			},

			lastOne() {
				if (this.current > 1) {
					this.current--;
				}
			},
			nextOne() {
				let index = this.current - 1;
				if (this.current < this.activity.votelist.length) {
					if(this.activity.votelist[index].tplx == '2' ){  //多选题 
						if(this.activity.votelist[index].zxs) {
							if(this.res[index] && this.res[index].length && this.res[index].length >= this.activity.votelist[index].zxs && this.res[index].length <= this.activity.votelist[index].zds) {
								this.current++;
							}else {
								uni.showToast({
									title: '请确认你的选择是否符合题目要求',
									icon: 'none'
								})
							}
						}else {
							this.current++;
						}
						
					}else {
						if(this.res[index] && this.res[index].length && this.res[index].length == 1) {
							this.current++;
						}else {
							uni.showToast({
								title: '请确认你的选择是否符合题目要求',
								icon: 'none'
							})
						}
						// this.current++;
					}
				}
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
		position: relative;
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
