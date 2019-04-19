<template>
	<view class="page">
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
				<view class="title">投票主题</view>
				<input placeholder="请填写投票主题" name="input"></input>
			</view>
			<!-- <view class="cu-form-group">
				<view class="title">投票对象</view>
				<input placeholder="请填写投票主题" name="input"></input>
			</view>
 -->
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
				<text class="text-xl text-bold text-blue">参评对象</text>
				<text class="bg-blue" style="width:3rem"></text>
			</view>
		</view>
		<view v-show="cpdxs.length" class="padding-sm">
			<view class='cu-tag round light bg-blue' v-for="(item, index) in cpdxs" :key="index" style="padding: 6px 14px;font-size: 16px;margin-bottom: 20upx;">
				{{item}}
				<image @tap="deleteOne(index)" src="../../static/icon/delete-blue.png" mode="" style="width: 36upx; height: 30upx;padding-left: 10upx;"></image>
			</view>
		</view>

		<view class="padding">
			<view class="justify-center text-blue" @tap="showModal" style="border: 1px dashed #ccc; border-radius: 10upx;height: 60upx;display: flex;align-items: center;font-size: 18px;">+</view>
		</view>
		<view class="cu-modal" :class="modalShow?'show':''">
			<view class="cu-dialog">
				<view class="cu-bar bg-white justify-end">
					<view class="content">{{modalTitle}}</view>
					<view class="action" @tap="hideModal">
						<text class="cuIcon-close text-red"></text>
					</view>
				</view>
				<view class="padding-xl justify-start" style="display: flex;">
					<text style="line-height: 60upx;margin-right: 20upx;">对象名字</text>
					<input v-model="cpdx" placeholder="请填写参评对象名字"></input>
				</view>
				<view class="cu-bar bg-white justify-end">
					<view class="action">
						<button class="cu-btn line-blue text-blue" @tap="hideModal">取消</button>
						<button class="cu-btn bg-blue margin-left" @tap="saveCpdx">确定</button>

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
			<view class="cu-card shadow" v-for="(option, index) in newOptions" :key="index" style="border: 1px solid #ededed;border-radius: 20upx;margin: 10upx 20upx;padding: 10upx 10upx 20upx;">
				<view class="cu-form-group">
					<view class="title">项目名称</view>
					<input v-model="option.xmmc" placeholder="请填写投票主题"></input>
				</view>
				<view class="cu-form-group">
					<view class="title">项目权重</view>
					<input v-model="option.xmqz" class="uni-input" type="number" placeholder="输入项目权重,如0.4" />
				</view>
				<view class="cu-form-group">
					<view class="title">最低分设置</view>
					<input v-model="option.zdf" class="uni-input" type="number" placeholder="最高分" />
				</view>
				<view class="cu-form-group">
					<view class="title">最高分设置</view>
					<input v-model="option.zgf" class="uni-input" type="number" placeholder="最低分" />
				</view>
			</view>
		</view>
		<view class="padding">
			<view class="justify-center text-blue" @tap="addOption" style="border: 1px dashed #ccc; border-radius: 10upx;height: 60upx;display: flex;align-items: center;font-size: 18px;">+</view>
		</view>
		<view style="text-align: center;">
			<button class="cu-btn bg-blue" @tap="saveOption" style="margin-top: 60upx;">保存该评分活动</button>
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				startDate: '',
				endDate: '',
				switchB: true,
				newOptions: [],
				modalShow: false,
				cpdx: '',
				modalTitle: '新增参评对象',
				cpdxs: [],
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
			
			deleteOne(index) {
				this.cpdxs.splice(index, 1);
			},
			saveCpdx() {
				this.modalShow = false;
				this.cpdxs.push(this.cpdx);
			},
			showModal() {
				this.modalShow = true;
				this.cpdx = '';
			},
			hideModal() {
				this.modalShow = false;
				console.log(this.cpdx)
			},
			saveOption() {
				
				console.log(this.newOptions)
			},
			addOption() {
				let item = [{
					xmmc: '',
					zgf: '',
					zdf: ''
				}];
				this.newOptions = this.newOptions.concat(item);
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
	.cu-form-group .title {
		min-width: calc(4em + 15px);
	}
</style>
