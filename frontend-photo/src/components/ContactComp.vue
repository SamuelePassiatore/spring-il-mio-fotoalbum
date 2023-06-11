<script>
import axios from 'axios';
const BASE_API_URL = "http://localhost:8080/api/v1";
export default {
    data() {
        return {
            email: "",
            message: "",
            messageSent: false
        };
    },
    methods: {
        sendMessage() {
            const formData = {
                email: this.email,
                message: this.message
            };

            axios.post(BASE_API_URL + "/contact/store", formData)
                .then(res => {
                    console.log(res.data);
                    this.messageSent = true;
                    this.email = "";
                    this.message = "";
                })
                .catch(err => console.log(err));
        }
    }
}
</script>

<template>
    <div class="p-3 text-center">
        <h1>Inviaci un messaggio</h1>
        <form @submit.prevent="sendMessage">
            <div class="d-flex justify-content-center align-items-center py-3">
                <label for="email" class="pe-2">Email</label><br>
                <input type="text" name="email" v-model="email" class="form-control w-25">
            </div>
            <div class="d-flex justify-content-center align-items-center py-3">
                <label for="message" class="pe-2">Messaggio</label><br>
                <textarea name="message" v-model="message" class="form-control w-50"></textarea>
            </div>
            <input type="submit" value="Invia" class="btn btn-small btn-success mt-3 me-2">
        </form>
        <p v-if="messageSent" class="text-success text-center fw-bold mt-3">Complimenti! Hai inviato il messaggio.</p>
    </div>
</template>

<style></style>