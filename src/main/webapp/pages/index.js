import Head from 'next/head'
import styles from '../styles/Home.module.css'
import Article from '../components/Article'

async function getPosts() {
    // let data=[
    //     {
    //         "id":"1",
    //         "title":"Title 1",
    //         "body":"Phasellus tortor nam lacus suspendisse nunc semper etiam turpis, pellentesque bibendum nostra platea auctor condimentum imperdiet, auctor nibh porttitor sapien in ornare aenean lacinia elit urna odio posuere pellentesque quisque id risus, malesuada et curae convallis ultrices inceptos eu, at nam ornare erat justo dictumst amet laoreet, iaculis molestie egestas volutpat aliquam curabitur neque lobortis lacus urna morbi mi ut, nunc cubilia potenti quis ullamcorper, vehicula auctor volutpat consectetur euismod faucibus per morbi sem pulvinar quam himenaeos sociosqu iaculis est, sollicitudin pulvinar commodo in mauris consequat inceptos taciti.",
    //         "author":"admin",
    //         "date":"1 nov 2020"
    //     },
    //     {
    //         "id":"2",
    //         "title":"Title 2",
    //         "body":"Phasellus tortor nam lacus suspendisse nunc semper etiam turpis, pellentesque bibendum nostra platea auctor condimentum imperdiet, auctor nibh porttitor sapien in ornare aenean lacinia elit urna odio posuere pellentesque quisque id risus, malesuada et curae convallis ultrices inceptos eu, at nam ornare erat justo dictumst amet laoreet, iaculis molestie egestas volutpat aliquam curabitur neque lobortis lacus urna morbi mi ut, nunc cubilia potenti quis ullamcorper, vehicula auctor volutpat consectetur euismod faucibus per morbi sem pulvinar quam himenaeos sociosqu iaculis est, sollicitudin pulvinar commodo in mauris consequat inceptos taciti.",
    //         "author":"admin",
    //         "date":"3 nov 2020"
    //     },
    //     {
    //         "id":"3",
    //         "title":"Title 3",
    //         "body":"Phasellus tortor nam lacus suspendisse nunc semper etiam turpis, pellentesque bibendum nostra platea auctor condimentum imperdiet, auctor nibh porttitor sapien in ornare aenean lacinia elit urna odio posuere pellentesque quisque id risus, malesuada et curae convallis ultrices inceptos eu, at nam ornare erat justo dictumst amet laoreet, iaculis molestie egestas volutpat aliquam curabitur neque lobortis lacus urna morbi mi ut, nunc cubilia potenti quis ullamcorper, vehicula auctor volutpat consectetur euismod faucibus per morbi sem pulvinar quam himenaeos sociosqu iaculis est, sollicitudin pulvinar commodo in mauris consequat inceptos taciti.",
    //         "author":"admin",
    //         "date":"8 nov 2020"
    //     }
    // ]

    const res = await fetch("http://localhost:8080/api/1")
    const data = await res.json()
    return data
}

function Home({content}) {
    console.log(content)
    return (
        <div className={styles.container}>
            <Head>
                <title>Aurelius</title>
                <link rel="icon" href="/favicon.ico"/>
            </Head>
            <main className={styles.main}>
                <h1 className={styles.title}>Welcome to Aurelius !</h1>
                {content.map((post) => (
                        <Article key={post.id} content={post}/>
                    )
                )}
                {/*<Article content={content}></Article>*/}
            </main>
        </div>
    )
}

// export async function getServerSideProps() {
//     // Fetch data from external API
//     const res = await fetch(`http://localhost:8080/api/posts`)
//     const content = await res.json()
//
//     // Pass data to the page via props
//     return { props: { content } }
// }

Home.getInitialProps  = async (ctx) => {
    const res = await fetch('http://localhost:8080/api/posts')
    const json = await res.json()
    return { content: json }
}

export default Home
