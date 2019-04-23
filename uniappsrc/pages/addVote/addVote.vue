<template>
	<view class="page">
		<login></login>
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
				<view class="title">投票主题</view>
				<input placeholder="请填写投票主题" name="input"></input>
			</view>
			<!-- <view class="cu-form-group">
				<view class="title">投票对象</view>
				<input placeholder="请填写投票主题" name="input"></input>
			</view> -->
			
			<view class="cu-form-group">
				<view class="title">开始日期</view>
				<picker mode="date" :value="startDate" start="2015-09-01" end="2020-09-01" @change="startDateChange">
					<view class="picker">
						{{startDate}}
					</view>
				</picker>
			</view>
			<view class="cu-form-group">
				<view class="title">结束日期</view>
				<picker mode="date" :value="endDate" start="2015-09-01" end="2020-09-01" @change="endDateChange">
					<view class="picker">
						{{endDate}}
					</view>
				</picker>
			</view>


			<!-- <view class="cu-form-group">
				<view class="title">是否匿名</view>
				<switch class='blur' @change="SwitchB" :class="switchB?'checked':''" :checked="switchB?true:false"></switch>
			</view> -->
			
			<view class="cu-form-group">
				<view class="title">投票说明</view>
				<textarea maxlength="-1" @input="textareaBInput" placeholder="多行文本输入框"></textarea>
			</view>
		</view>
		<view class="cu-bar justify-start bg-white margin-top">
			<view class="action border-title">
				<text class="text-xl text-bold text-blue">投票题目</text>
				<text class="bg-blue" style="width:3rem"></text>
			</view>
		</view>
		<view class="padding">
			<view class="justify-center text-blue" @tap="gotoVoteTitle" style="border: 1px dashed #ccc; border-radius: 10upx;height: 60upx;display: flex;align-items: center;font-size: 18px;">+</view>
		</view>
		<!-- <view ref="input1" style="border: 1px solid #ccc; padding: 10upx;"></view> -->
	</view>
</template>

<script>
	export default {
		data() {
			return {
				startDate: '',
				endDate: '',
				switchB: true

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
		methods: {
			gotoVoteTitle() {
				uni.navigateTo({
					url: '../../pages/addVoteTitle/addVoteTitle'
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
.cu-form-group .title {
		min-width: calc(4em + 15px);
	}
</style>
