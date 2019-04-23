<template>
	<view class="page">
		<login></login>
		<cu-custom v-show="isBack" bgColor="bg-blue" :isBack="isBack">
			<block slot="content" style="width: calc(100% - 100px);">科研组专家打分活动</block>
		</cu-custom>

		<view class="vote-wrapper padding">
			<view class="vote-tt title" style="font-size: 18px;">
				2019科研组专家打分活
			</view>
			<view class="vote-tt" style="margin-top: 40upx;">
				考评期限：<text class="vote-subtt">2019-01-02 至 2019-02-01</text>
			</view>
			<view class="vote-tt">
				考评说明：<text class="vote-subtt">投票说明投票说明投票说明投票说明投票说明</text>
			</view>

			<view class="padding">
				<view class="mark-item">
					<text class="mark-name">参评对象</text>
					<text class="mark-status">打分状态</text>
					<text class="mark-opt">操作</text>
				</view>
				<view class="mark-item">
					<text class="mark-name">lucy</text>
					<text class="mark-status">未完成</text>
					<text class="mark-opt">
						<text @tap="gotoMarkTitle" class='cu-tag radius light bg-blue'>打分</text>
					</text>
				</view>
				<view class="mark-item">
					<text class="mark-name">Lily</text>
					<text class="mark-status">未完成</text>
					<text class="mark-opt">
						<text @tap="gotoMarkTitle" class='cu-tag radius light bg-blue'>打分</text>
					</text>
				</view>
				<view class="mark-item">
					<text class="mark-name">张三</text>
					<text class="mark-status">未完成</text>
					<text class="mark-opt">
						<text @tap="gotoMarkTitle" class='cu-tag radius light bg-blue'>打分</text>
					</text>
				</view>
			</view>
			
			<view style="margin-top: 50upx;text-align: center;">
				<button v-show="!js" class="cu-btn bg-blue shadow-blur">提交打分结果</button>
				<button v-show="js == 1" @tap="gotoCodePage" class="cu-btn bg-blue shadow-blur">发布活动并生成二维码</button>
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
				ID: null,
				js: null
				
			};
		},
		mounted() {
			
		},
		onLoad(params) {
			this.ID = params.id; //获取到轰动id来更新数据
			this.js = params.js
			if (getCurrentPages().length > 1) {
				this.isBack = true;
			}
		},
		methods: {
			gotoCodePage() {
				uni.navigateTo({
					url: '../../pages/code/code?id=10'
				})
			},
			gotoMarkTitle() {
				uni.navigateTo({
					url: '../../pages/markTitle/markTitle'
				})
			}
			
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
	.mark-item {
		display: flex;
		flex-wrap: nowrap;
		justify-content: space-around;
		align-items: center;
		margin-bottom: 20upx;
		.mark-name, .mark-status, .mark-opt{
			width: 200upx;
			text-align: center;
			flex: 1;
		}
	}
</style>
