<template>
	<view class="page">
		<login></login>
		<view v-show="addBtnShow" class="opt-btn shadow-blur bg-blue" @tap="gotoUser" style="position:fixed; left: 650upx; bottom: 50upx;">
			<image src="../../static/img/user-white.png" mode="" style="width: 40upx;height: 40upx;"></image>
		</view>
		<view v-show="addBtnShow" class="opt-btn shadow-blur bg-blue" @tap="addBtnShowChange" style="">
			+
		</view>
		<view v-show="!addBtnShow" class="opt-btn-grounp">
			<button class="cu-btn shadow-blur bg-blue" style="margin-left: 30upx;" @tap="gotoVote">投票类</button>
			<button class="cu-btn shadow-blur bg-blue" style="margin-left: 30upx;" @tap="gotoMark">打分类</button>
			<button class="cu-btn shadow-blur bg-blue" style="margin-left: 30upx;" @tap="addBtnShowChange">x</button>
		</view>
		<view class="cu-bar search bg-white" style="position: fixed; width: 750upx; top: 0; left: 0;z-index: 1000;">
			<view class="search-form round">
				<text class="cuIcon-search"></text>
				<input @focus="InputFocus" @blur="InputBlur" :adjust-position="false" type="text" placeholder="搜索投票、打分活动等"
				 confirm-type="search"></input>
			</view>
			<view class="action">
				<button class="cu-btn bg-blue light shadow-blur round">搜索</button>
			</view>
		</view>
		<view style="margin-top: 100upx;">
			<view v-show="wfbList && wfbList.length" @tap="wfbShow = !wfbShow" class="cu-bar justify-start bg-white">
				<view class="action sub-title">
					<text class="text-xl text-bold text-orange">未发布</text>
					<text class="bg-orange" style="width:2rem"></text>
				</view>
				<view v-show="!wfbShow" style="font-size: 12px;color: #aaa;margin-left: 20upx;">已隐藏</view>
			</view>
			<view v-show="wfbShow" class="cu-list menu-avatar">
				<view class="cu-item" :class="modalName=='move-box-'+ index?'move-cur':''" v-for="(wfb,index) in wfbList" :key="index"
				 @touchstart="ListTouchStart" @touchmove="ListTouchMove" @touchend="ListTouchEnd" :data-target="'move-box-' + index">
					<!-- <image class="cu-avatar round lg" src="../../static/img/vote3.png" mode=""></image> -->
					<!-- <view class="cu-avatar round lg" :style="[{backgroundImage:'url(https://ossweb-img.qq.com/images/lol/web201310/skin/big2100'+ (index+2) +'.jpg)'}]"></view> -->
					<view class="content" @tap="editOne(index)">
						<view class="text-grey">
							{{wfb.bt}}
							<view class='cu-tag line-orange radius' style="margin-left: 20upx;">{{wfb.hdlx ? '评分活动' : '投票活动'}}</view>
							<!-- <view v-show="wfb.hdlx" class='cu-tag line-orange radius' style="margin-left: 20upx;">{{wfb.scoreoblist && wfb.scoreoblist.length}}个评分对象</view> -->
							<!-- <view v-show="wfb.hdlx" class='cu-tag line-orange radius' style="margin-left: 20upx;">{{wfb.scorelist && wfb.scorelist.length}}个评分内容</view> -->
							<!-- <view v-show="!wfb.hdlx" class='cu-tag line-orange radius' style="margin-left: 20upx;">{{(wfb.votelist && wfb.votelist.length)|| 0}}个题目</view> -->
						</view>
						<view class="text-gray text-sm">
							<text class="cuIcon-infofill text-red  margin-right-xs"></text> {{wfb.kssj.substring(0,11)}} ~
							{{wfb.jssj.substring(0,11)}}</view>
					</view>

					<view class="move">
						<view @tap="gotoPage(wfbList, index)" class="bg-green">
							发布
						</view>
						<view @tap="deleteOne(index)" class="bg-red">删除</view>
					</view>
				</view>
			</view>
		</view>

		<view class="page-block">
			<view @tap="yfbShow = !yfbShow" class="cu-bar justify-start bg-white">
				<view class="action sub-title">
					<text class="text-xl text-bold text-green">已发布</text>
					<text class="bg-green" style="width:2rem"></text>
				</view>
				<view v-show="!yfbShow" style="font-size: 12px;color: #aaa;margin-left: 20upx;">已隐藏</view>
			</view>
			<view v-show="yfbShow" class="cu-timeline">
				<template v-for="(item, index) in yfbList">
					<view class="cu-time">{{item.jssj.substring(5, 11)}}</view>
					<view class="cu-item">
						<view class="content" @tap="gotoPage(yfbList, index)">
							<view class="cu-tag bg-green light radius">{{item.hdlx == '0' ? '投票活动' : '评分活动'}}</view>
							【{{item.bt}}】
						</view>
					</view>
				</template>

				<!-- <view class="cu-item">
					<view class="content">
						<view class="cu-tag bg-green light radius">10:00</view> 【工会科室会议表决】
					</view>
				</view>
				<view class="cu-item">
					<view class="content">
						<view class="cu-tag bg-green light radius">10:00</view>
						<navigator url="../../pages/mark/mark?id=10">【科研组专家打分活动】</navigator>
					</view>
				</view> -->
			</view>
		</view>

		<view class="page-block">
			<view @tap="ywcShow = !ywcShow" class="cu-bar justify-start bg-white">
				<view class="action sub-title">
					<text class="text-xl text-bold text-blue">已完成</text>
					<text class="bg-blue" style="width:2rem"></text>
				</view>
				<view v-show="!ywcShow" style="font-size: 12px;color: #aaa;margin-left: 20upx;">已隐藏</view>
			</view>
			<view v-show="ywcShow" class="cu-timeline">
				<template v-for="(item, index) in ywcList">
					<view class="cu-time">{{item.jssj.substring(5, 11)}}</view>
					<view class="cu-item" :class="index%2?'text-cyan':'text-blue'">
						<view class="content shadow-blur" :class="index%2?'bg-cyan':'bg-blue'" @tap="goToPage(index)">
							<view class="cu-tag bg-white light radius" :class="index%2?'text-cyan':'text-blue'">{{item.hdlx == '0' ? '投票活动' : '评分活动'}}</view>
							【{{item.bt}}】
							<image class="timeline-item-delete" src="../../static/icon/delete-white.png" mode=""></image>
						</view>
					</view>
				</template>
				<!-- <view class="cu-time">昨天</view>
				<view class="cu-item text-blue">
					<view class="content bg-blue shadow-blur">
						<text>20:00</text>
						<navigator url="../voteCount/voteCount">【2019XX党支部支部互评】</navigator>
						<image class="timeline-item-delete" src="../../static/icon/delete-white.png" mode=""></image>
					</view>
					<view class="content bg-cyan shadow-blur">
						<text>10:00</text>
						<navigator url="../markCount/markCount"> 【开发组-专业组技术带头人打分】</navigator>
						<image class="timeline-item-delete" src="../../static/icon/delete-white.png" mode=""></image>
					</view>
				</view>
				<view class="cu-time">03-10</view>
				<view class="cu-item text-blue">
					<view class="content bg-blue shadow-blur">
						<text>20:00</text> 【开发组-专业组技术带头人打分】
						<image class="timeline-item-delete" src="../../static/icon/delete-white.png" mode=""></image>
					</view>
				</view>

				<view class="cu-time">02-01</view>
				<view class="cu-item text-blue">
					<view class="bg-blue content shadow-blur">
						<text>20:00</text> 【2019XX党支部支部互评】
						<image class="timeline-item-delete" src="../../static/icon/delete-white.png" mode=""></image>
					</view>
				</view> -->
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				wfbList: [],
				yfbList: [],
				ywcList: [],
				wfbShow: true,
				yfbShow: true,
				ywcShow: true,
				title: 'Hello',
				modalName: null,
				addBtnShow: true,
				token: this.$token
			}
		},
		onLoad() {
			this.$api.get('/theme/findAll').then(res => {
				console.log('res', res);
			})
		},
		onShow() {
			this.getData();
		},
		mounted() {

		},
		computed: {
			logined() {
				return this.$store.state.logined
			}
		},
		watch: {
			logined: {
				handler(newVal, oldVal) {
					console.log(newVal, oldVal)
					if (newVal) {
						this.getData();
					}
				},
				immediate: true
			}
		},
		methods: {
			goToActivityPage(index) {

			},
			gotoPage(data, index) {
				if (data[index].hdlx) { //hdlx: 0 投票， 1 打分
					uni.navigateTo({
						url: '../../pages/mark/mark?hdid=' + data[index].id
					})
				} else {
					uni.navigateTo({
						url: '../../pages/vote/vote?hdid=' + data[index].id
					})
				}
			},
			getData() {
				this.$api.post('/theme/findAll').then(res => {
					if (res.resCode == 200) {
						this.wfbList = res.data.wfb;
						this.yfbList = res.data.yfb;
						this.ywcList = res.data.ywc;
					}
				})
			},
			deleteOne(index) {
				uni.showModal({
					title: '提示',
					content: '是否确定删除本次活动？',
					success: (res) => {
						if (res.confirm) {
							this.$api.post('/theme/delete', {
								hdid: this.wfbList[index].id
							}).then(res => {
								if (res.resCode == 200) {
									this.getData();
									uni.showToast({
										title: '删除成功！',
										icon: 'none'
									})
								} else {
									uni.showToast({
										title: res.resMsg,
										icon: 'none'
									})
								}
							})
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});

			},
			editOne(index) {
				if (this.wfbList[index].hdlx) { //hdlx: 0 投票， 1 打分
					uni.navigateTo({
						url: '../../pages/addMark/addMark?hdid=' + this.wfbList[index].id
					})
				} else {
					uni.navigateTo({
						url: '../../pages/addVote/addVote?hdid=' + this.wfbList[index].id
					})
				}
			},
			gotoUser() {
				uni.navigateTo({
					url: '../../pages/user/user'
				})
			},
			gotoVote() {
				uni.navigateTo({
					url: '../../pages/addVote/addVote'
				})
			},
			gotoMark() {
				uni.navigateTo({
					url: '../../pages/addMark/addMark'
				})
			},
			addBtnShowChange() {
				this.addBtnShow = !this.addBtnShow;
			},
			// ListTouch触摸开始
			ListTouchStart(e) {
				this.listTouchStart = e.touches[0].pageX
			},

			// ListTouch计算方向
			ListTouchMove(e) {
				this.listTouchDirection = e.touches[0].pageX - this.listTouchStart > 0 ? 'right' : 'left'
			},

			// ListTouch计算滚动
			ListTouchEnd(e) {
				if (this.listTouchDirection == 'left') {
					this.modalName = e.currentTarget.dataset.target
				} else {
					this.modalName = null
				}
				this.listTouchDirection = null
			}
		}
	}
</script>

<style>
	.opt-btn-grounp {
		position: fixed;
		bottom: 60upx;
		left: 0;
		width: 750upx;
		height: 80upx;
		display: flex;
		justify-content: center;
		align-items: center;
		z-index: 900;
	}

	.opt-btn {
		position: fixed;
		bottom: 50upx;
		left: 335upx;
		width: 80upx;
		height: 80upx;
		display: flex;
		justify-content: center;
		align-items: center;
		border-radius: 50%;
		color: #FFFFFF;
		font-weight: 500;
		font-size: 22px;
		z-index: 900;
	}

	.page-block {
		margin-top: 40upx;
	}

	.timeline-item-delete {
		width: 35upx;
		height: 35upx;
		position: absolute;
		top: 6px;
		right: 6px;
	}
</style>
