/**
 * Created by alexandraneamtu on 08/01/2018.
 */

import React, { Component } from 'react';
import {View, Text, Button, TextInput, ActivityIndicator} from 'react-native';
import firebase from 'firebase';


class LoginForm extends Component {
    static navigationOptions = {
        //title: 'Home',
        header:null,
    };
    state = { email: '', password: '', error: '' };


    render() {
        const {navigate} = this.props.navigation;
        return (
            <View>
                <View style={styles.header}>
                    <Text style={styles.headerText}>- List of Books -</Text>
                </View>
                <TextInput
                    label='Email Address'
                    placeholder='email'
                    value={this.state.email}
                    onChangeText={email => this.setState({ email })}
                />
                <TextInput
                    label='Password'
                    autoCorrect={false}
                    placeholder='*******'
                    secureTextEntry
                    value={this.state.password}
                    onChangeText={password => this.setState({ password })}
                />
                <Text style={styles.errorTextStyle}>{this.state.error}</Text>
                <Button style={styles.buttonStyle} color="#E91E63" onPress={ () =>
                {
                    const { email, password } = this.state;
                    firebase.auth().signInWithEmailAndPassword(email, password)
                        .then(() => { this.setState({ error: '', email:"", password:"" });navigate('BookList')})
                        .catch(() => { console.log("!!!!!!!erroreeerererere!!!!!!!")
                            this.setState({ error: 'Authentication failed.'});
                        });
                }
                }
                        title="Log in" />
            </View>
        );
    }
}
const styles = {
    errorTextStyle: {
        color: '#E64A19',
        alignSelf: 'center',
        paddingTop: 10,
        paddingBottom: 10
    },
    header:{
        backgroundColor: '#E91E63',
        alignItems: 'center',
        justifyContent: 'center',
        borderBottomWidth: 10,
        borderBottomColor: '#ddd',
        //marginTop:-40,

    },
    headerText:{
        color: 'white',
        fontSize: 18,
        padding: 26,
    },
    buttonStyle:{
        color: "#E91E63"
    }
};

export default LoginForm;