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
		
		<view>
			<view class="cu-bar justify-start bg-white">
				<view class="action sub-title">
					<text class="text-xl text-bold text-orange">未发布</text>
					<text class="bg-orange" style="width:2rem"></text>
				</view>
			</view>
			<view class="cu-list menu-avatar">
				<view class="cu-item radius shadow bg-white margin-top margin" :class="modalName=='move-box-'+ index?'move-cur':''"
				 v-for="(item,index) in 4" :key="index" @touchstart="ListTouchStart" @touchmove="ListTouchMove" @touchend="ListTouchEnd"
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

		<view>
			<view class="cu-bar justify-start bg-white">
				<view class="action sub-title">
					<text class="text-xl text-bold text-green">未完成</text>
					<text class="bg-green" style="width:2rem"></text>
				</view>
			</view>
			<view>
				item1
			</view>
			<view>
				item2
			</view>
		</view>

		<view>
			<view class="cu-bar justify-start bg-white">
				<view class="action sub-title">
					<text class="text-xl text-bold text-blue">已完成</text>
					<text class="text-ABC text-blue">Done</text>
				</view>
			</view>
			<view class="cu-timeline">
				<view class="cu-time">昨天</view>
				<view class="cu-item cur cuIcon-noticefill">
					<view class="content bg-green shadow-blur">
						<text>22:22</text> 【广州市】快件已到达地球
					</view>
				</view>
				<view class="cu-item text-red cuIcon-attentionforbidfill">
					<view class="content bg-red shadow-blur">
						这是第一次，我家的铲屎官走了这么久。久到足足有三天！！
					</view>
				</view>
				<view class="cu-item text-grey cuIcon-evaluate_fill">
					<view class="content bg-grey shadow-blur">
						这是第一次，我家的铲屎官走了这么久。
					</view>
				</view>
				<view class="cu-item text-blue">
					<view class="bg-blue content">
						<text>20:00</text> 【月球】快件已到达月球，准备发往地球
					</view>
					<view class="bg-cyan content">
						<text>10:00</text> 【银河系】快件已到达银河系，准备发往月球
					</view>
				</view>
			</view>

			<view class="cu-timeline">
				<view class="cu-time">06-17</view>
				<view class="cu-item">
					<view class="content">
						<text>01:30</text> 【喵星】 MX-12138 已揽收，准备发往银河系
					</view>
				</view>
			</view>

			<view class="cu-timeline">
				<view class="cu-time">06-17</view>
				<view class="cu-item">
					<view class="content">
						<view class="cu-capsule radius">
							<view class="cu-tag bg-cyan">上午</view>
							<view class="cu-tag line-cyan">10:00</view>
						</view>
						<view class="margin-top">这是第一次，我家的铲屎官走了这么久。久到足足有三天！！ 在听到他的脚步声响在楼梯间的那一刻，我简直想要破门而出，对着他狠狠地吼上10分钟，然后再看心情要不要他进门。</view>
					</view>
				</view>
				<view class="cu-item text-blue">
					<view class="bg-blue shadow-blur content">
						<view class="cu-list menu-avatar radius">
							<view class="cu-item">
								<view class="cu-avatar round lg" style="background-image:url(https://ossweb-img.qq.com/images/lol/web201310/skin/big10006.jpg);"></view>
								<view class="content">
									<view class="text-grey">文晓港</view>
									<view class="text-gray text-sm">
										<text class="cuIcon-infofill text-red"></text> 消息未送达</view>
								</view>
								<view class="action">
									<view class="text-grey text-xs">22:20</view>
									<view class="cu-tag round bg-grey sm">5</view>
								</view>
							</view>
							<view class="cu-item">
								<view class="cu-avatar round lg" style="background-image:url(https://ossweb-img.qq.com/images/lol/web201310/skin/big10006.jpg);">
									<view class="cu-tag badge">99+</view>
								</view>
								<view class="content">
									<view class="text-grey">文晓港
										<view class="cu-tag round orange sm">SVIP</view>
									</view>
									<view class="text-gray text-sm">
										<text class="cuIcon-redpacket_fill text-red"></text> 收到红包</view>
								</view>
								<view class="action">
									<view class="text-grey text-xs">22:20</view>
									<text class="cuIcon-notice_forbid_fill text-gray"></text>
								</view>
							</view>
						</view>
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
					url:'../../pages/vote/vote'
				})
			},
			gotoMark() {
				uni.navigateTo({
					url: '../../pages/mark/mark'
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

	/* input[type='file'] {
		outline: none; border: 0;
	} */
	/* .content {
		text-align: center;
		height: 400upx;
	}

	.logo {
		height: 200upx;
		width: 200upx;
		margin-top: 200upx;
	}

	.title {
		font-size: 36upx;
		color: #8f8f94;
	} */
</style>
