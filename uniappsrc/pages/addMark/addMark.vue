<template>
	<view class="page">
		<login :loginFormShow="loginFormShow" @hide="loginHide"></login>
		<cu-custom bgColor="bg-blue" :isBack="true">
			<block slot="content">发起评分活动</block>
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
		</view>
		<view class="padding">
			<view class="justify-center text-blue dash-btn" @tap="saveTheme">save</view>
		</view>
		<view class="cu-bar justify-start bg-white margin-top">
			<view class="action border-title">
				<text class="text-xl text-bold text-blue">参评对象</text>
				<text class="bg-blue" style="width:3rem"></text>
			</view>
		</view>
		<view v-show="cpdxs.length" class="padding-sm">
			<view @tap="updateOne(index)" class='cu-tag round light bg-blue' v-for="(item, index) in cpdxs" :key="index" style="padding: 6px 14px;font-size: 16px;margin-bottom: 20upx;">
				{{item.dx}}
				<image @tap="deleteOne(index)" src="../../static/icon/delete-blue.png" mode="" style="width: 36upx; height: 30upx;padding-left: 10upx;"></image>
			</view>
		</view>

		<view class="padding">
			<view class="justify-center text-blue dash-btn" @tap="showModal">+</view>
			<!-- <view class="justify-center text-blue" @tap="saveObj" style="border: 1px dashed #ccc; border-radius: 10upx;height: 60upx;display: flex;align-items: center;font-size: 18px;">save</view> -->
		</view>
		<view class="cu-modal" :class="modalShow?'show':''" style="text-align: center;">
			<view class="cu-dialog">
				<view class="cu-bar bg-white justify-end">
					<view class="content">{{modalTitle}}</view>
					<view class="action" @tap="hideModal">
						<text class="cuIcon-close text-red"></text>
					</view>
				</view>
				<view class="padding-xl">
					<view class="justify-start" style="display: flex;">
						<text style="line-height: 60upx;margin-right: 20upx;">对象名字</text>
						<input v-model="cpdx.dx" placeholder="请填写参评对象名字"></input>
					</view>
					<view class="justify-start" style="display: flex;">
						<text style="line-height: 60upx;margin-right: 20upx;">对象描述</text>
						<textarea v-model="cpdx.nr" maxlength="-1" placeholder="请填写参评对象描述"></textarea>
					</view>
				</view>
				<view class="cu-bar bg-white justify-end">
					<view class="action">
						<button class="cu-btn line-blue text-blue" @tap="hideModal">取消</button>
						<button class="cu-btn bg-blue margin-left" @tap="saveCpdx">保存</button>
					</view>
				</view>
			</view>
		</view>

		<view class="cu-bar justify-start bg-white margin-top">
			<view class="action border-title">
				<text class="text-xl text-bold text-blue">评分项目</text>
				<text class="bg-blue" style="width:3rem"></text>
			</view>
		</view>
		<view>
			<view @tap="updateOneOp(index)" class="cu-card shadow scoreOp-item" v-for="(option, index) in newOptions" :key="index">
				<view class="cu-form-group">
					<view class="title">评分内容</view>
					{{option.mc}}
				</view>
				<view class="cu-form-group">
					<view class="title">分数设置</view>
					{{option.zdf}} ~ {{option.zgf}}
				</view>
				<view class="cu-form-group">
					<view class="title">详细描述</view>
					{{option.mcms}}
				</view>
				<view class="vote-item-del" @tap="deleteOneOp(index)">
					<image src="../../static/img/del.png" mode="" style="width: 45upx;height: 45upx;"></image>
				</view>
			</view>
		</view>
		<view class="padding">
			<view class="justify-center text-blue dash-btn" @tap="addOption">+</view>
		</view>
		<view class="padding" style="text-align: center;">
			<button class="cu-btn bg-blue" @tap="createCode" style="margin-top: 60upx;">提前生成二维码</button>
		</view>
		<view class="cu-modal" :class="modalOpShow?'show':''" style="text-align: center;">
			<view class="cu-dialog">
				<view class="cu-bar bg-white justify-end">
					<view class="content">{{modalOpTitle}}</view>
					<view class="action" @tap="hideOpModal">
						<text class="cuIcon-close text-red"></text>
					</view>
				</view>
				<view class="padding-xl">
					<view class="justify-start" style="display: flex;">
						<text style="line-height: 60upx;margin-right: 20upx;">评分内容</text>
						<input v-model="newOption.mc" placeholder="请填写投票主题"></input>
					</view>
					<view class="justify-start" style="display: flex;">
						<text style="line-height: 60upx;margin-right: 20upx;">详细描述</text>
						<textarea v-model="newOption.mcms" maxlength="-1" placeholder="可不填"></textarea>
					</view>
					<view class="justify-start" style="display: flex;">
						<text style="line-height: 60upx;margin-right: 20upx;">分数设置</text>
						<view style="border: 1px solid #cdcdcd;border-radius: 10upx;">
							<input v-model="newOption.zdf" type="number" style="width: 150upx;text-align: center;"></input>
						</view>
						<text style="display: flex;justify-content: center;align-items: center;width: 40upx;">-</text>
						<view style="border: 1px solid #cdcdcd;border-radius: 10upx;">
							<input v-model="newOption.zgf" type="number" style="width: 150upx;text-align: center;"></input>
						</view>
					</view>
				</view>
				<view class="cu-bar bg-white justify-end">
					<view class="action">
						<button class="cu-btn line-blue text-blue" @tap="hideOpModal">取消</button>
						<button class="cu-btn bg-blue margin-left" @tap="saveScoreOp">保存</button>
					</view>
				</view>
			</view>
		</view>
		<Modal :modalShow="errorModalShow" modalTitle="警告（Warning）" modalContent="请先填写并保存活动的基本设置!" @hide="hideErrorModal"></Modal>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				loginFormShow: false,
				activityForm: {
					bt: '',
					nr: '',
					hdlx: 1,
					sffb: 0,
					kssj: '',
					jssj: '',
					sfnm: 0
				},
				paramsHdid: null,
				newOption: {},
				newOptions: [],
				modalShow: false,
				cpdx: {},
				modalTitle: '新增参评对象',
				cpdxs: [],
				modalOpShow: false,
				modalOpTitle: '新增评分项目',
				errorModalShow: false
			};
		},
		onLoad(params) {
			this.paramsHdid = params.hdid;
			if(!this.getLogined()) {
				this.loginFormShow = true;
			}
		},
		onShow() {
			if (this.paramsHdid || this.activityForm.id) {
				this.getData();
			}
		},
		methods: {
			loginHide() {
				this.loginFormShow = false;
				if (this.paramsHdid || this.activityForm.id) {
					this.getData();
				}
			},
			createCode() {
				console.log(this.paramsHdid || this.activityForm.id)
				uni.navigateTo({
					url: '../../pages/code/code?lx=mark&id=' + (this.paramsHdid || this.activityForm.id) + '&title=' + this.activityForm
						.bt +
						'&kssj=' + this.activityForm.kssj + '&jssj=' + this.activityForm.jssj
				})
			},
			hideErrorModal() {
				this.errorModalShow = false;
			},
			deleteOneOp(index) {
				this.$api.post('/score/deleteScoreOneOp', this.newOptions[index]).then(res => {
					if (res.resCode == 200) {
						uni.showToast({
							title: '删除成功！',
							icon: 'none'
						})
						this.getData();
					} else if(res.resCode == 100) {
						this.loginFormShow = true;
					}
				})
			},
			hideOpModal() {
				this.modalOpShow = false;
			},
			saveScoreOp() {
				uni.showLoading({
					title: '保存中...'
				})
				this.$api.post('/score/updateScoreOneOp', this.newOption).then(res => {
					uni.hideLoading();
					this.modalOpShow = false;
					if (res.resCode == 200) {
						this.getData();
						uni.showToast({
							title: '保存成功',
							icon: 'none'
						})
					} else if(res.resCode == 100) {
						this.loginFormShow = true;
					}
				})
			},
			getData(id) {
				uni.showLoading({
					title: '加载中'
				});
				this.$api.post('/theme/findAllInfoById', {
					id: id || this.paramsHdid || this.activityForm.id
				}).then((res) => {
					uni.hideLoading()
					if (res.resCode == 200) {
						this.activityForm = res.data;
						this.activityForm.kssj = this.activityForm.kssj && this.activityForm.kssj.substring(0, 11);
						this.activityForm.jssj = this.activityForm.jssj && this.activityForm.jssj.substring(0, 11);
						this.cpdxs = this.activityForm.scorelist || [];
						this.newOptions = this.activityForm.scoreoblist || [];
					}else if(res.resCode == 100) {
						this.loginFormShow = true;
					}
				})
			},
			saveObj() {
				for (let i = 0, len = this.cpdxs.length; i < len; i++) {
					this.cpdxs[i].hdid = this.paramsHdid;
				}
				this.$api.post('/score/addScoreDx', JSON.stringify(this.cpdxs)).then(res => {
					if (res.resCode == 200) {
						uni.showToast({
							title: '保存成功！',
							icon: 'none'
						})
					} else if(res.resCode == 100) {
						this.loginFormShow = true;
					}
				})
			},
			hideModal2() {
				this.modalShow2 = false;
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
							console.log(key, postData[key])
							this.errorModalShow = true;
							// this.modalContent = '请完成所有设置选项。';
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
						this.getData(res.data.id);
						uni.showToast({
							title: '保存成功',
							icon: 'none'
						})
					} else {
						uni.showToast({
							title: res.resMsg,
							icon: 'none'
						})
					}
				})
			},
			deleteOne(index) {
				this.$api.post('/score/deleteScoreOneDx', this.cpdxs[index]).then(res => {
					if (res.resCode == 200) {
						uni.showToast({
							title: '删除成功！',
							icon: 'none'
						})
						this.getData();
					} else if(res.resCode == 100) {
						this.loginFormShow = true;
					}
				})
			},
			saveCpdx() {
				uni.showLoading({
					title: '保存中...'
				})
				this.$api.post('/score/updateScoreOneDx', this.cpdx).then(res => {
					uni.hideLoading();
					this.modalShow = false;
					if (res.resCode == 200) {
						this.getData();
						uni.showToast({
							title: '保存成功',
							icon: 'none'
						})
					} else if(res.resCode == 100) {
						this.loginFormShow = true;
					}
				})

			},
			updateOne(index) {
				this.modalOpTitle = '修改参评对象';
				this.modalShow = true;
				this.cpdx = this.cpdxs[index];
			},
			showModal() {
				if (this.paramsHdid || this.activityForm.id) {
					this.modalShow = true;
					this.cpdx = {
						dx: '',
						nr: '',
						hdid: this.paramsHdid || this.activityForm.id
					};
				} else {
					this.errorModalShow = true;
				}

			},
			hideModal() {
				this.modalShow = false;
			},
			saveOption() {
				for (let i = 0, len = this.newOptions.length; i < len; i++) {
					this.newOptions[i].hdid = this.paramsHdid;
				}
				this.$api.post('/score/addScoreOP', JSON.stringify(this.newOptions)).then(res => {
					if (res.resCode == 200) {
						uni.showToast({
							title: '保存成功',
							icon: 'none'
						})
					} else if(res.resCode == 100) {
						this.loginFormShow = true;
					}
				})
			},
			updateOneOp(index) {
				this.newOption = this.newOptions[index];
				this.modalOpTitle = "修改评分项目";
				this.modalOpShow = true;
			},
			addOption() {
				if (this.paramsHdid || this.activityForm.id) {
					this.newOption = {
						zdf: 0,
						zgf: 100,
						mc: '',
						hdid: this.paramsHdid || this.activityForm.id
					}
					this.modalOpShow = true;
				} else {
					this.errorModalShow = true;
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

	.scoreOp-item {
		border: 1px solid #ededed;
		border-radius: 20upx;
		margin: 20upx 30upx;
		padding: 10upx 10upx 20upx;
		position: relative;
	}
</style>
