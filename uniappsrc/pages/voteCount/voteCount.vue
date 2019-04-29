<template>
	<view class="page">
		<login></login>
		<cu-custom v-show="isBack" bgColor="bg-blue" :isBack="isBack">
			<block slot="content" style="width: calc(100% - 100px);">{{hdData.bt}}</block>
		</cu-custom>
		<view class="vote-wrapper padding-xl">
			<view class="vote-tt title" style="font-size: 18px;">
				{{hdData.bt}} - 统计结果
			</view>
			<view class="vote-tt" style="margin-top: 20upx;">
				评分期限：<text class="vote-subtt">{{hdData.kssj && hdData.kssj.substring(0, 11)}} 至 {{hdData.kssj && hdData.jssj.substring(0, 11)}}</text>
			</view>

			<view class="">
				<view class="ques-item" v-for="(res, index) in result">
					<view class="ques-tt">
						{{index + 1}}.{{res.vote.bt}}
					</view>
					<view class="">
						<view class="ques-td" v-for="(opt, optIndex) in res.result">
							<text v-show="opt.xxlx == '1' || opt.xxlx == '3' || opt.xxlx == '4'" class="opt-name">{{opt.xxmc}}（{{opt.sl}}票）
								<text v-show="opt.xxlx == '4' && opt.sl" @tap="lookDetail(res.vote.bt, opt.dxid)" class='cu-tag line-blue radius' style="margin-left: 20upx;border-color: transparant;">查看</text>
								<!-- <text   style="display: inline-block;border: 1px solid #ededed;padding: 0 10upx;border-radius">查看</text> -->
							</text>
							<view v-show="opt.xxlx == '2'" class="opt-name" style="width: 200upx height: 150upx;">
								<image :src="opt.xxmc" mode="" style="width: 180upx; height: 150upx;margin-right: 20upx;"></image> ({{opt.sl}}票)
							</view>
							<view v-show="opt.xxlx == '1' || opt.xxlx == '3' || opt.xxlx == '4'" class="cu-progress round sm striped">
								<view :class="'bg-' + (colorList[Math.floor(Math.random()*5)])" :style="[{ width: (opt.sl/res.num) * 100 ? (opt.sl/res.num) * 100+'%' :''}]">{{(opt.sl/res.num) * 100}}% </view>
							</view>
						</view>
					</view>
				</view>
			</view>

		</view>
		
		<view class="cu-modal" :class="modalShow?'show':''">
			<view class="cu-dialog">
				<view class="cu-bar bg-white justify-end">
					<view class="content">{{name}}</view>
					<view class="action" @tap="modalHide">
						
						<text class="cuIcon-close text-red"></text>
					</view>
				</view>
				<view class="padding-xl">
					<view v-show="item.bz" class="modal-item" v-for="(item, index) in modalData">
						{{item.bz}}
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
				hdData: {},
				result: [],
				percent: '48%',
				colorList: [
					'orange', 'green', 'red', 'blue', 'purple', 'pink'
				],
				modalShow: false,
				name: '',
				modalData: []
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
		methods:{
			lookDetail(name, id) {
				this.$api.post('/voting/findAllQtByQid', {id: id}).then(res => {
					if(res.resCode == 200) {
						this.name = name;
						this.modalShow = true;
						this.modalData = res.data;
						console.log(this.modalData )
					}else {
						uni.showToast({
							title: "获取数据失败！",
							icon: 'none'
						})
					}
				})
			},
			modalHide() {
				this.modalShow = false;
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
						for(let i = 0; i < this.result.length; i ++) {
							let num = 0;
							for(let j = 0, len = this.result[i].result.length; j < len; j ++) {
								if(this.result[i].result[j].sl == 0) {
									break;
								}else {
									num += this.result[i].result[j].sl;
								}
							}
							this.result[i].num = num;
							// console.log(this.result[i].num)
						}
					}
				})
			}
		}
	}
</script>

<style lang="scss">
	.ques-item {
		margin: 40upx 0 20upx;

		.ques-tt {
			line-height: 2;
		}

		.ques-td {
			display: flex;
			font-size: 12px;
			padding: 0upx 0 10upx;
			flex-direction: column;
			justify-content: flex-start;
			align-items: flex-start;

			.opt-name {
				line-height: 2;
			}
		}
	}
	.modal-item {
		border: 1px solid #cdcdcd;
		border-radius: 10upx;
		padding: 10upx 20upx;
		line-height: 2;
		margin: 20upx;
		font-size: 14px;
	}
</style>
