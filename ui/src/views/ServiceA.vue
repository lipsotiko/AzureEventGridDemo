<template>
  <div>
    <h1>Service A</h1>
    <textarea v-model="message" rows="40" cols="100" />
    <button @click="sendMessage">Send</button>
  </div>
</template>
<script>
import axios from 'axios'
export default {
  name: 'ServiceA',
  data: () => ({
    message: null
  }),
  methods: {
    sendMessage () {
      axios.post('/api/service-a/event/create', {
        message: this.message
      })
    }
  }
}
</script>
