<template>
	<view class="page">
		<login :loginFormShow="loginFormShow" @hide="loginHide"></login>
		<cu-custom bgColor="bg-blue" isBack="true">
			<block slot="content" style="width: calc(100% - 100px);">用户管理</block>
		</cu-custom>
		<view class="padding" style="margin-top: 40upx;">
			<view class="cu-form-group">
				<view class="title">用户姓名</view>
				<view class="input-wrapper">
					<input v-model="userForm.mc" placeholder="请填写用户姓名" name="input" style="padding-left: 20upx;"></input>
				</view>
			</view>
			<view class="cu-form-group">
				<view class="title">系统账号</view>
				<view class="input-wrapper">
					<input v-model="userForm.zh" placeholder="请填写系统账号" name="input" style="padding-left: 20upx;"></input>
				</view>
			</view>
			<view class="cu-form-group">
				<view class="title">系统角色</view>
				<view class="">
					<radio-group class="radio-sex" @change="jsChange">
						<label class="radio-single">
							<radio value="1" :checked="userForm.js == 1"/>系统管理员
						</label>
						<label class="radio-single">
							<radio value="0" :checked="userForm.js == 0"/>普通用户
						</label>
					</radio-group>
					<!-- <input v-model="userForm.zh" placeholder="请填写系统账号" name="input" style="padding-left: 20upx;"></input> -->
				</view>
			</view>
			<view class="cu-form-group">
				<view class="title">登录密码</view>
				<view class="input-wrapper">
					<input v-model="userForm.mm" placeholder="请设置登录密码" name="input" style="padding-left: 20upx;"></input>
				</view>
			</view>
			<view class="padding" style="text-align: center;">
				<button class="cu-btn bg-blue" @tap="saveUser" style="margin-top: 60upx;padding: 0 70upx;">保存该用户</button>
			</view>
			
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				loginFormShow: false,
				userForm: {}
			};
		},
		onLoad() {
			if(!this.getLogined()) {
				this.loginFormShow = true;
			}
		},
		methods: {
			loginHide() {
				this.loginFormShow = false;
				if (this.getLogined() && this.hdid && this.obid) {
					this.getData();
				}
			},
			jsChange(e) {
				this.userForm.js = e.detail.value;
			},
			saveUser() {
				console.log(this.userForm)
				this.$api.post('/user/add', this.userForm).then(res => {
					if(res.resCode == 200) {
						uni.showToast({
							title: "保存成功！",
							icon: 'none',
							duration: 2000
						})
						this.userForm = {
							zh: '',
							mm: '',
							js: null,
							mc: ''
						}
					}else if(res.resCode == 100) {
						this.loginFormShow = true;
					}
				})
			}
		}
	}
</script>

<style lang="scss">
.radio-sex {
	display: flex;
	flex-direction: column;
}

.radio-single {
	padding: 20upx 20upx;
}
</style>
