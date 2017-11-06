import React, { Component } from 'react';
import {
    AppRegistry,
    FlatList,
    StyleSheet,
    Text,
    View,
    TouchableOpacity,
    ScrollView,
    Image,
    Alert,
    TextInput,
    Linking
} from 'react-native';
import {StackNavigator} from 'react-navigation'


var books=[
    {
        key:'1',
        title: 'Gone with the wind',
        author: 'Suzanne Collins',
        description: "The nation of Panem, formed from a post-apocalyptic North America, is a country that consists of a wealthy Capitol region surrounded by 12 poorer districts. Early in its history, a rebellion led by a 13th district against the Capitol resulted in its destruction and the creation of an annual televised event known as the Hunger Games. In punishment, and as a reminder of the power and grace of the Capitol, each district must yield one boy and one girl between the ages of 12 and 18 through a lottery system to participate in the games. The 'tributes' are chosen during the annual Reaping and are forced to fight to the death, leaving only one survivor to claim victory." +
        "\n" +
        "When 16-year-old Katniss's young sister, Prim, is selected as District 12's female representative, Katniss volunteers to take her place. She and her male counterpart Peeta, are pitted against bigger, stronger representatives, some of whom have trained for this their whole lives. , she sees it as a death sentence. But Katniss has been close to death before. For her, survival is second nature.",
        image: require('./img/gone_with_the_wind.jpg')},
    {
        key:'2',
        title: 'Pride and Prejudice',
        author: 'Jane Austen',
        description: 'It is a truth universally acknowledged, that a single man in possession of a good fortune must be in want of a wife.” So begins Pride and Prejudice, Jane Austen’s witty comedy of manners—one of the most popular novels of all time—that features splendidly civilized sparring between the proud Mr. Darcy and the prejudiced Elizabeth Bennet as they play out their spirited courtship in a series of eighteenth-century drawing-room intrigues. Renowned literary critic and historian George Saintsbury in 1894 declared it the “most perfect, the most characteristic, the most eminently quintessential of its author’s works,” and Eudora Welty in the twentieth century described it as “irresistible and as nearly flawless as any fiction could be.',
        image: require('./img/pride_and_prejudice.jpg')},
    {
        key:'3',
        title: 'The Hunger Games',
        author: 'Margaret Mitchell',
        description: "Gone with the Wind is a novel written by Margaret Mitchell, first published in 1936. The story is set in Clayton County, Georgia, and Atlanta during the American Civil War and Reconstruction era. It depicts the struggles of young Scarlett O'Hara, the spoiled daughter of a well-to-do plantation owner, who must use every means at her disposal to claw her way out of the poverty she finds herself in after Sherman's March to the Sea. A historical novel, the story is a Bildungsroman or coming-of-age story, with the title taken from a poem written by Ernest Dowson." +
        "\n" +
        "Gone with the Wind was popular with American readers from the onset and was the top American fiction bestseller in the year it was published and in 1937. As of 2014, a Harris poll found it to be the second favorite book of American readers, just behind the Bible. More than 30 million copies have been printed worldwide.",
        image: require('./img/the_hunger_games.jpg')},
]




class BookList extends Component {
    static navigationOptions = {
        //title: 'Home',
        header:null,
    };
    render() {
        const {navigate} = this.props.navigation;
        return (
            <View style={styles.container}>
                <View style = {styles.header}>
                    <Text style = {styles.headerText}>- List of Books -</Text>
                </View>

                 <FlatList
                    data = { books }
                    renderItem={
                        ({item}) =>
                            <ScrollView style = {styles.scrollContainer} >
                                <View style={styles.linearView} >
                                    <Image style= {{ height:70, width: 50, resizeMode: 'contain' }}
                                       source={item.image} />
                                    <Text style={styles.item} onPress={
                                        () => navigate('Details',{ book : item })
                                    } >{item.title}</Text>
                                </View>
                            </ScrollView>
                        }
                 />
                <View style = {styles.footer}>
                    <TouchableOpacity style={styles.reserveButton} onPress={() =>
                            navigate('Page2')}>
                        <Text style={styles.reserveButtonText}> Prepare a book </Text>
                    </TouchableOpacity>
                </View>

    </View>
    );
    }
}

class Page2 extends Component{
    render() {
        return (
            <View>
                <Text> Title: </Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray'}}
                    onChangeText={(title) => this.setState({title})}

                />
                <Text style={{ marginTop:28}}> Name: </Text>
                <TextInput
                style={{height: 40, borderColor: 'gray', marginBottom:8}}
                onChangeText={(name) => this.setState({name})}
                />
                <TouchableOpacity style={styles.reserveButton}>
                    <Text style={styles.reserveButtonText}
                          onPress={() => {
                              receiver = "bogdanmorariu96@gmail.com";
                              subject = "Reserve a book";
                              body = "Title: " + this.state.title + "\n" +
                                  "  Name: " + this.state.name;
                              all = "mailto:" + receiver + "?subject=" + subject + "&body=" + body ;
                              Linking.openURL(all)}}> Reserve </Text>
                </TouchableOpacity>

            </View>
        );
    }
}


class Details extends Component{
    static navigationOptions = {
        //title: 'Home',
        header:null,
    };
    render() {
        const {state} = this.props.navigation;
        var book = state.params ? state.params.book : "<undefined>";
        return (
            <ScrollView>
            <View style={{
                justifyContent: 'center',
                alignItems: 'center',
            }}>
                <Text style = {styles.bookTitle}>- {book.title} -</Text>
                <TextInput style = {{width:150,textAlign:'center'}}> {book.author} </TextInput>
                <Image style={styles.detailedImage} source={book.image}/>
                <ScrollView>
                    <TextInput style={{height: 300, width: 350, marginTop:10 }} multiline={true}> {book.description} </TextInput>
                </ScrollView>
            </View>
            </ScrollView>
        );
    }
}

const NavigationApp = StackNavigator({
    Home: {screen: BookList},
    Page2: {screen: Page2},
    Details: {screen: Details}
});



const styles = StyleSheet.create({
    container: {
        flex: 1,
        //paddingTop: 22
    },
    header:{
        backgroundColor: '#E91E63',
        alignItems: 'center',
        justifyContent: 'center',
        borderBottomWidth: 10,
        borderBottomColor: '#ddd',
        //marginTop:-40,

    },
    item: {
        padding: 10,
        fontSize: 18,
        height: 44,
    },
    headerText:{
        color: 'white',
        fontSize: 18,
        padding: 26,
    },
    footer: {
        position: 'absolute',
        alignItems: 'center',
        bottom: 0,
        left: 0,
        right: 0,
    },
    reserveButton: {
        backgroundColor: '#E91E63',
        //borderRadius: 30,
        borderColor: '#ccc',
        alignItems: 'center',
        justifyContent: 'center',
        marginBottom:45
    },
    reserveButtonText: {
        color:'#fff',
        fontSize:24,
    },
    linearView: {
        flexDirection:'row',
        padding:8,
    },
    bookTitle:{
        color:'#E91E63',
        fontSize:25,
        textAlign:'center',
    },
    detailedImage: {
        height:220,
        width: 200,
        resizeMode: 'contain',
        marginBottom:28,
        marginTop:28
    }


});

export default class App extends Component{
    render(){
        return <NavigationApp/>;
    }
}



// skip this line if using Create React Native App
AppRegistry.registerComponent('AwesomeProject', () => App);