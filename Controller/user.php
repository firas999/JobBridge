<?php

namespace App\Controller;
use App\Repository\CandidatRepository;
use App\Security\verifierdroit;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\EncoderFactoryInterface;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;


/**
* @Route("/user")
*/
class user extends AbstractController
{
/**
* @Route("/index", name="user_index")
*/
public function index()
{
return $this->render('user/user.html.twig');
}}