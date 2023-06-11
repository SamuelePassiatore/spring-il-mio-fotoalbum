<script>
import PhotoComp from '../PhotoComp.vue';
import ContactComp from '../ContactComp.vue';
import axios from 'axios';
const BASE_API_URL = "http://localhost:8080/api/v1";

export default {
    data() {
        return {
            photo: [],
            filter: '',
            filteredPhoto: []
        }
    },
    components: {
        PhotoComp, ContactComp
    },
    methods: {
        getPhotos() {
            axios.get(BASE_API_URL + "/photos")
                .then(res => {

                    const photo = res.data;
                    console.log(photo);
                    if (this.filter) {
                        this.filteredPhoto = photo.filter(photo => {

                            return photo.title.toLowerCase().includes(this.filter.toLowerCase());
                        });
                    } else {
                        this.filteredPhoto = photo;
                    }

                    this.photo = photo;
                })
                .catch(err => console.log(err));
        }
    },
    mounted() {
        this.getPhotos();
    }
}

</script>

<template>
    <div class="d-flex flex-column align-items-center">
        <h1 class="p-2">Fotoalbum</h1>
        <input type="text" v-model="filter" placeholder="Filtra per nome" class="form-control m-2 w-50"
            @keyup.enter="getPhotos()">
        <p v-if="filteredPhoto.length === 0" class="text-danger pt-4">Nessuna foto trovata con questo titolo.</p>
        <PhotoComp class="p-2" v-for="photo in filteredPhoto" :photo="photo" />
    </div>
    <ContactComp />
</template>

<style scoped></style>