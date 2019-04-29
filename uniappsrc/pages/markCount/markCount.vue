<template>
	<view class="page">
		<login></login>
		<cu-custom v-show="isBack" bgColor="bg-blue" :isBack="isBack">
			<block slot="content" style="width: calc(100% - 100px);">{{hdData.bt}}</block>
		</cu-custom>
		<view class="vote-wrapper padding">
			<view class="vote-tt title" style="font-size: 18px;">
				{{hdData.bt}} - 统计结果
			</view>
			<view class="vote-tt" style="margin-top: 40upx;">
				评分期限：<text class="vote-subtt">{{hdData.kssj && hdData.kssj.substring(0, 11)}} 至 {{hdData.kssj && hdData.jssj.substring(0, 11)}}</text>
			</view>

			<view class="margin-top">
				<view class="count-item bg-blue light" style="height: 80upx;">
					<view class="count-item-index">排名</view>
					<view class="count-item-name">对象姓名</view>
					<view class="count-item-score">总分</view>
					<view class="count-item-detail" style="text-align: center;">得分详情</view>
				</view>
				<view v-for="(item, index) in result" class="count-item bg-blue light">
					<view class="count-item-index">{{index + 1}}</view>
					<view class="count-item-name">{{item.name}}</view>
					<view class="count-item-score">{{(Math.round(item.df*100)/100).toFixed(1)}}</view>
					<view class="count-item-detail" @tap="lookDetail(item.name, item.qid)">
						查看
					</view>
				</view>
			</view>
		</view>

		<!-- <view style="margin-top: 50upx;text-align: center;">
			<button class="cu-btn bg-blue shadow-blur">分享打分统计结果</button>
		</view> -->
		
		<view class="cu-modal" :class="modalShow?'show':''">
			<view class="cu-dialog">
				<view class="cu-bar bg-white justify-end">
					<view class="content">{{name}}-得分详情</view>
					<view class="action" @tap="modalHide">
						<text class="cuIcon-close text-red"></text>
					</view>
				</view>
				<view class="padding-xl">
					<view class="modal-item">
						<text class="modal-item-mc">考评项目</text>
						<!-- <text class="modal-item-qj">打分区间</text> -->
						<text class="modal-item-df">得分</text>
					</view>
					<view class="modal-item" v-for="(item, index) in modalData">
						<text class="modal-item-mc">{{item.mc}}</text>
						<!-- <text class="modal-item-qj">[{{item.zdf}},{{item.zgf}}]</text> -->
						<text class="modal-item-df">{{(Math.round(item.df*100)/100).toFixed(2)}}</text>
					</view>
					
				</view>
			</view>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				isBack: false,
				hdid: null,
				hdData: {},
				result: [],
				modalData: [],
				modalShow: false,
				name: ''
			};
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
					if (newVal && this.hdid) {
						this.getData();
					}
				},
				immediate: true
			}
		},
		onShow() {
			if (this.logined && this.hdid) {
				this.getData();
			}
		},
		onLoad(params) {
			this.hdid = params.hdid;
			if (getCurrentPages().length > 1) {
				this.isBack = true;
			}
			if (this.logined && this.hdid) {
				this.getData();
			}
		},
		methods: {
			modalHide() {
				this.modalShow = false;
			},
			lookDetail(name, qid) {
				this.$api.post('/score/getResultByQid', {id: qid}).then(res => {
					if(res.resCode == 200) {
						this.modalData = res.data;
						this.name = name;
						this.modalShow = true;
					}else {
						uni.showToast({
							title: '获取数据失败！',
							icon: 'none'
						})
					}
				})
			},
			getData() {
				uni.showLoading({
					title: '加载中'
				});
				this.$api.post('/theme/getResult', {
					hdid: this.hdid
				}).then(res => {
					uni.hideLoading()
					if (res.resCode == 200) {
						this.hdData = res.data.hddata;
						this.result = res.data.result;
					}
				})
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

		.count-item-index,
		.count-item-name,
		.count-item-score,
		.count-item-detail {
			flex: 1;
			text-align: center;
			height: auto;
		}

		// 		.count-item-index {
		// 			width: 70upx;
		// 		}
		// 		.count-item-name {
		// 			width: 140upx;
		// 		}
		// 		.count-item-score {
		// 			width: 80upx;
		// 		}
		// 		.count-item-detail {
		// 			width: 390upx;
		// 			padding-left: 10upx;
		// 			
		// 		}
	}
	.modal-item {
		display: flex;
		justify-content: space-between;
		align-items: center;
		color: #333;
		font-size: 12px;
		// border-bottom: 1px solid #eee;
		text {
			flex: 1;
			text-align: center;
			height: auto;
			line-height: 2;
		}
		
	}
</style>
