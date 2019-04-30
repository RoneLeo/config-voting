<template>
	<view class="page">
		<login :loginFormShow="loginFormShow" @hide="loginHide"></login>
		<cu-custom bgColor="bg-blue" :isBack="true">
			<block slot="content">发起投票活动</block>
		</cu-custom>
		<view class="cu-bar justify-start bg-white margin-top">
			<view class="action border-title">
				<text class="text-xl text-bold text-blue">基本设置</text>
				<text class="bg-blue" style="width:3rem"></text>
			</view>
		</view>
		<view class="">
			<view class="cu-form-group">
				<view class="title">活动标题</view>
				<input v-model="activityForm.bt" placeholder="请填写活动标题" name="input"></input>
			</view>
			<view class="cu-form-group">
				<view class="title">开始日期</view>
				<picker mode="date" :value="activityForm.kssj" start="2019-01-01" end="2120-01-01" @change="startDateChange">
					<view class="picker">
						{{activityForm.kssj}}
					</view>
				</picker>
			</view>
			<view class="cu-form-group">
				<view class="title">结束日期</view>
				<picker mode="date" :value="activityForm.jssj" start="2019-01-01" end="2120-01-01" @change="endDateChange">
					<view class="picker">
						{{activityForm.jssj}}
					</view>
				</picker>
			</view>
			<view class="cu-form-group">
				<view class="title">活动内容</view>
				<textarea v-model="activityForm.nr" maxlength="-1" placeholder="请输入活动内容"></textarea>
			</view>
			<view class="padding">
				<view class="justify-center text-blue" @tap="saveTheme" style="border: 1px dashed #ccc; border-radius: 10upx;height: 60upx;display: flex;align-items: center;font-size: 18px;margin-top: 20upx;">save</view>
			</view>
		</view>
		<view class="cu-bar justify-start bg-white margin-top">
			<view class="action border-title">
				<text class="text-xl text-bold text-blue">投票题目</text>
				<text class="bg-blue" style="width:3rem"></text>
			</view>
		</view>
		<view class="vote-list">
			<view class="vote-item" v-for="(vote, index) in votelist">
				<view class="vote-item-bt">
					<text class="vote-index">{{index+1}}.</text>
					<text class='cu-tag radius bg-blue' style="margin-right: 20upx;">{{myJson.vote[vote.tplx]}}</text>
					{{vote.bt}}
					<text v-show="vote.tplx!=1">(可选{{vote.zxs}}-{{vote.zds}}个选项)</text>
				</view>
				<view class="vote-item-opt" v-for="(opt, optIndex) in vote.volist">
					<text class="vote-index">{{index+1}}.{{optIndex + 1}}</text> <text v-show="opt.xxlx==1 || opt.xxlx==3">{{opt.xxmc}}</text>
					<view v-show="opt.xxlx==2" style="padding: 10upx;">
						<image :src="opt.xxmc" mode="" style="width: 200upx; height: 150upx;"></image>
					</view>
					<text v-show="opt.xxlx==4">
						其他（用户自行填写）
					</text>
				</view>
				<view class="vote-item-del" @tap="deleteOne(index)">
					<image src="../../static/img/del.png" mode="" style="width: 45upx;height: 45upx;"></image>
				</view>
			</view>
		</view>
		<view class="padding">
			<view class="justify-center text-blue" @tap="gotoVoteTitle" style="border: 1px dashed #ccc; border-radius: 10upx;height: 60upx;display: flex;align-items: center;font-size: 18px;">+</view>
		</view>
		<view class="padding" style="text-align: center;">
			<button class="cu-btn bg-blue" @tap="createCode" style="margin-top: 60upx;">提前生成二维码</button>
		</view>
		<Modal :modalShow="modalShow" modalTitle="警告（Warning）" :modalContent="modalContent" @hide="hideModal"></Modal>
	</view>
</template>

<script>
	import {
		mapState,
		mapMutations
	} from 'vuex'
	import myJson from '../../static/radio.js'

	export default {
		data() {
			return {
				loginFormShow: false,
				paramsHdid: null,
				activityForm: {
					bt: '',
					nr: '',
					hdlx: 0,
					sffb: 0,
					kssj: '',
					jssj: ''
				},
				votelist: [],
				startDate: '',
				endDate: '',
				modalShow: false,
				modalContent: '',
				myJson: myJson

			};
		},
		computed: {
			hdid: {
				get() {
					return this.$store.state.voteData.id
				}
			}
		},
		onLoad(params) {
			this.paramsHdid = params.hdid;
			if (!this.getLogined()) {
				this.loginFormShow = true;
			}
		},
		onShow() {
			if (this.paramsHdid || this.hdid) {
				this.getData();
			}
		},
		mounted() {

		},
		methods: {
			...mapMutations(['saveVoteData', 'resetVoteData']),
			loginHide() {
				this.loginFormShow = false;
				if (this.paramsHdid || this.hdid) {
					this.getData();
				}
			},
			getData() {
				this.$api.post('/theme/findAllInfoById', {
					id: this.paramsHdid || this.hdid
				}).then((res) => {
					if (res.resCode == 200) {
						this.activityForm = res.data;
						this.activityForm.kssj = this.activityForm.kssj && this.activityForm.kssj.substring(0, 11);
						this.activityForm.jssj = this.activityForm.jssj && this.activityForm.jssj.substring(0, 11);
						this.votelist = this.activityForm.votelist || [];
					} else if (res.resCode == 100) {
						this.loginFormShow = true;
					}
				})
			},
			createCode() {
				uni.navigateTo({
					url: '../../pages/code/code?lx=mark&id=' + (this.paramsHdid || this.activityForm.id) + '&title=' + this.activityForm
						.bt +
						'&kssj=' + this.activityForm.kssj + '&jssj=' + this.activityForm.jssj
				})
			},
			deleteOne(index) {
				uni.showModal({
					title: '提示',
					content: '此操作将永久删除本题目, 是否继续?？',
					success: (res) => {
						if (res.confirm) {
							let postData = JSON.parse(JSON.stringify(this.votelist[index]));
							delete postData.volist;
							this.$api.post('/voting/deleteOne', postData).then(res => {
								if (res.resCode == 200) {
									uni.showToast({
										title: '删除成功！',
										icon: 'none'
									})
								} else if (res.resCode == 100) {
									this.loginFormShow = true;
								}
							})
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});
				console.log(this.votelist[index])
			},
			hideModal() {
				this.modalShow = false;
			},
			saveTheme() {
				console.log(JSON.parse(JSON.stringify(this.activityForm)));
				let postData = {
					bt: this.activityForm.bt,
					nr: this.activityForm.nr,
					hdlx: this.activityForm.hdlx,
					sffb: this.activityForm.sffb,
					kssj: this.activityForm.kssj,
					jssj: this.activityForm.jssj
				}
				for (let key in postData) {
					if (key != 'nr') {
						if (postData[key] === '' || postData[key] === null) {
							this.modalContent = '请完成所有设置选项。';
						}
					}

				}
				let url = '/theme/add';
				if (this.activityForm.id) {
					url = '/theme/update';
					postData.id = this.activityForm.id;
				}

				this.$api.post(url, Object.assign({}, postData)).then(res => {
					if (res.resCode == 200) {
						this.saveVoteData(res.data.id)
						uni.showToast({
							title: '保存成功',
							icon: 'none'
						})
					} else if (res.resCode == 100) {
						this.loginFormShow = true;
					}
				})
			},
			gotoVoteTitle() {
				if (this.paramsHdid) {
					this.saveVoteData(this.paramsHdid);
				}
				if (Object.prototype.toString.call(this.hdid) == '[object Null]') {
					this.modalShow = true;
					this.modalContent = '需要先完成活动及基本设置，并成功保存后，才能对其添加题目哦。';
				} else {
					uni.navigateTo({
						url: '../../pages/addVoteTitle/addVoteTitle'
					})
				}
			},

			startDateChange(e) {
				this.activityForm.kssj = e.detail.value
			},
			endDateChange(e) {
				this.activityForm.jssj = e.detail.value
			},
		}
	}
</script>

<style lang="scss">
	.cu-form-group .title {
		min-width: calc(4em + 15px);
	}

	.vote-list {
		padding: 20upx 30upx;

		.vote-item {
			margin-bottom: 20upx;
			border: 1px solid #dedede;
			border-radius: 10upx;
			padding: 40upx 30upx 30upx;
			color: #999;
			font-size: 14px;
			position: relative;

			.vote-index {
				display: inline-block;
				width: 60upx;
				text-align: left;
			}

			.vote-index,
			.vote-item-bt {
				color: #555;
			}

			.vote-item-bt,
			.vote-item-opt {
				line-height: 2.1;
			}

			.vote-item-opt {
				display: flex;
				align-items: flex-start;
			}


		}
	}
</style>
