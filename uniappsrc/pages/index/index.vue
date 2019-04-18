<template>
	<view class="page">
		<view v-show="addBtnShow" class="opt-btn shadow-blur bg-blue" @tap="addBtnShowChange" style="">
			+
		</view>
		<view v-show="!addBtnShow" class="opt-btn-grounp">
			<button class="cu-btn shadow-blur bg-blue" style="margin-left: 30upx;" @tap="gotoVote">投票类</button>
			<button class="cu-btn shadow-blur bg-blue" style="margin-left: 30upx;" @tap="gotoMark">打分类</button>
			<button class="cu-btn shadow-blur bg-blue" style="margin-left: 30upx;" @tap="addBtnShowChange">x</button>
		</view>
		<view class="cu-bar search bg-white">
			<view class="search-form round">
				<text class="cuIcon-search"></text>
				<input @focus="InputFocus" @blur="InputBlur" :adjust-position="false" type="text" placeholder="搜索投票、打分活动等" confirm-type="search"></input>
			</view>
			<view class="action">
				<button class="cu-btn bg-blue shadow-blur round">搜索</button>
			</view>
		</view>
		<view>
			<view class="cu-bar justify-start bg-white">
				<view class="action sub-title">
					<text class="text-xl text-bold text-orange">未发布</text>
					<text class="bg-orange" style="width:2rem"></text>
				</view>
			</view>
			<view class="cu-list menu-avatar">
				<view class="cu-item radius shadow bg-white margin-top margin" :class="modalName=='move-box-'+ index?'move-cur':''"
				 v-for="(item,index) in 2" :key="index" @touchstart="ListTouchStart" @touchmove="ListTouchMove" @touchend="ListTouchEnd"
				 :data-target="'move-box-' + index">
					<view class="cu-avatar round lg" :style="[{backgroundImage:'url(https://ossweb-img.qq.com/images/lol/web201310/skin/big2100'+ (index+2) +'.jpg)'}]"></view>
					<view class="content">
						<view class="text-grey">文晓港</view>
						<view class="text-gray text-sm">
							<text class="cuIcon-infofill text-red  margin-right-xs"></text> 消息未送达</view>
					</view>
					<view class="action">
						<view class="text-grey text-xs">22:20</view>
						<view class="cu-tag round bg-grey sm">5</view>
					</view>
					<view class="move">
						<view class="bg-grey">置顶</view>
						<view class="bg-red">删除</view>
					</view>
				</view>
			</view>
		</view>

		<view class="page-block">
			<view class="cu-bar justify-start bg-white">
				<view class="action sub-title">
					<text class="text-xl text-bold text-green">未完成</text>
					<text class="bg-green" style="width:2rem"></text>
				</view>
			</view>
			<view class="cu-timeline">
				<view class="cu-time">06-17</view>
				
					<view class="cu-item">
						<view class="content">
							<view class="cu-tag bg-green light radius">01:00</view><navigator url="../../pages/vote/vote"> 【2019九室爱岗敬业选举】</navigator>
						</view>
					</view>
				
				
				<view class="cu-item">
					<view class="content">
						<view class="cu-tag bg-green light radius">10:00</view> 【工会科室会议表决】
					</view>
				</view>
				<view class="cu-item">
					<view class="content">
						<view class="cu-tag bg-green light radius">10:00</view>
						<navigator url="../../pages/mark/mark">【科研组专家打分活动】</navigator>
					</view>
				</view>
			</view>

		</view>

		<view class="page-block">
			<view class="cu-bar justify-start bg-white">
				<view class="action sub-title">
					<text class="text-xl text-bold text-blue">已完成</text>
					<text class="bg-blue" style="width:2rem"></text>
				</view>
			</view>
			<view class="cu-timeline">
				<view class="cu-time">昨天</view>
				<view class="cu-item text-blue">
					<view class="content bg-blue shadow-blur">
						<text>20:00</text> <navigator url="../voteCount/voteCount">【2019XX党支部支部互评】</navigator>
						<image class="timeline-item-delete" src="../../static/icon/delete-white.png" mode=""></image>
					</view>
					<view class="content bg-cyan shadow-blur">
						<text>10:00</text><navigator url="../markCount/markCount"> 【开发组-专业组技术带头人打分】</navigator>
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
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import cuCustom from '../../colorui/components/cu-custom.vue'
	export default {
		components: {
			cuCustom
		},
		data() {
			return {
				title: 'Hello',
				modalName: null,
				addBtnShow: true
			}
		},
		onLoad() {

		},
		methods: {
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
		z-index: 1000;
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
		z-index: 1000;
	}
	
	.page-block {
		margin-top: 40upx;
	}
	
	.timeline-item-delete {
		width: 35upx;height: 35upx;position: absolute;top: 6px;right: 6px;
	}

</style>
