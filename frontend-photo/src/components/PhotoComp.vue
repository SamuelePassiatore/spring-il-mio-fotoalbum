<script>
export default {
    data() {
        return {
            open: false,
        }
    },
    props: {
        photo: {}
    }
}
</script>

<template>
    <ul class="text-center">
        <li>
            <div @click="open = !open" role="button" class="pb-2">
                {{ photo.title }}
            </div>
            <div v-if="open">
                <img :src="photo.url" :alt="photo.title" class="pb-2">
                <p>{{ photo.description }}</p>
            </div>
        </li>
    </ul>
</template>

<style scoped>
img {
    width: 100px;
}

li {
    list-style-type: none;
}
</style>