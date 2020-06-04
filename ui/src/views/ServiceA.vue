<template>
  <div>
    <h1>Service A</h1>
    <div>
      <div>
        <textarea
          v-model="message"
          rows="40"
          placeholder="Input a message you would like to publish..."
        />
      </div>
      <button @click="sendMessage">Send</button>
    </div>
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
<style scoped>
textarea {
  width: 100%;
}

button {
  float: right;
}
</style>
