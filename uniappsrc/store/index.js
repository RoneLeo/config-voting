import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
const store = new Vuex.Store({
	// strict: process.env.NODE_ENV !== 'production',
	state: {
		markData: {
			id: null
		},
		voteData: {
			id: null
		},
		logined: false
	},
	getters: {
		getMarkId: state => {
			return state.markData.id
		},
		getVoteId: state => {
			return state.voteData.id
		}
	},
	mutations: {
		setLogin(state, bool) {
			state.logined = bool;
		},
		saveVoteData(state, id) {
			console.log('id', id);
			state.voteData.id = id;
		}
// 		resetFormData(state){
// 			state.formData = {
// 				bz: '',
// 				wz: '四川省成都市',
// 				pic: '',
// 				audios: [],
// 				imgs: [],
// 				videos: []
// 			}
// 		},
// 		saveFormData(state, form) {
// 			const oldForm = state.formData;
// 			state.formData = Object.assign(oldForm, form);
// 			console.log('state.formData', state.formData);
// 		},
// 		login(state, provider) {
// 			console.log(state)
// 			console.log(provider)
// 			state.login = true;
// 			state.token = provider.token;
// 			state.userName = provider.userName;
// 			state.avatarUrl = provider.avatarUrl;
// 		},
// 		logout(state) {
// 			state.login = false;
// 			state.token = '';
// 			state.userName = '';
// 			state.avatarUrl = '';
// 		}
	}
})
export default store
