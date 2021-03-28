<?php

namespace App\Controller;

use App\Entity\Candidat;
use App\Entity\Employee;
use App\Entity\Urlizer;
use App\Entity\User;
use App\Form\CandidatType;
use App\Form\EmployeeType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\EncoderFactoryInterface;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;



/**
 * @Route("/home")
 */
class HomeController extends AbstractController
{
    /**
     * @Route("/index", name="indexhome")
     */
    public function index(): Response
    {
        return $this->render('home/index.html.twig');
    }


    private EncoderFactoryInterface $encoder;
    private UserPasswordEncoderInterface $pwdEncoder;
    public function __construct(EncoderFactoryInterface $encoder,UserPasswordEncoderInterface $enc)
    {
        $this->encoder = $encoder;
        $this->pwdEncoder = $enc;
    }
    /**
     * @Route("/newcandidat", name="homecandidatfront_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $candidat = new Candidat();
        $salt = md5(microtime());
        $form = $this->createForm(CandidatType::class, $candidat);
        $form->handleRequest($request);
        $encoder = $this->encoder->getEncoder(User::class);
        if ($form->isSubmitted() && $form->isValid())
        { /** @var UploadedFile $uploadedFile */
            $uploadedFile = $form['imageFile']->getData();
            $destination = $this->getParameter('kernel.project_dir').'/public/uploads';
            $originalFilename = pathinfo($uploadedFile->getClientOriginalName(), PATHINFO_FILENAME);
            $newFilename = Urlizer::urlize($originalFilename).'-'.uniqid().'.'.$uploadedFile->guessExtension();
            $uploadedFile->move(
                $destination,
                $newFilename
            );
            $candidat->setImg($newFilename);
            $entityManager = $this->getDoctrine()->getManager();
            $encodedPassword=$encoder->encodePassword($candidat->getMotdepasse(),$salt);
            $candidat->setMotdepasse($this->pwdEncoder->encodePassword($candidat,$candidat->getMotdepasse()));
            $candidat->setRoles(['ROLE_CANDIDATE']);

            $entityManager->persist($candidat);
            $entityManager->flush();

            return $this->redirectToRoute('indexhome');}


        return $this->render('candidat_front/new.html.twig', [
            'candidat' => $candidat,
            'form' => $form->createView(),
        ]);
    }

        /**
         * @Route("/new", name="homeemployee_new", methods={"GET","POST"})
         */
        public function newe(Request $request): Response
    {
        $employee = new Employee();
        $salt = md5(microtime());
        $form = $this->createForm(EmployeeType::class, $employee);
        $form->handleRequest($request);
        $encoder = $this->encoder->getEncoder(User::class);

        if ($form->isSubmitted() && $form->isValid())
        { /** @var UploadedFile $uploadedFile */
            $uploadedFile = $form['imageFile']->getData();
            $destination = $this->getParameter('kernel.project_dir').'/public/uploads';
            $originalFilename = pathinfo($uploadedFile->getClientOriginalName(), PATHINFO_FILENAME);
            $newFilename = Urlizer::urlize($originalFilename).'-'.uniqid().'.'.$uploadedFile->guessExtension();
            $uploadedFile->move(
                $destination,
                $newFilename
            );
            $employee->setImg($newFilename);



            {
                $entityManager = $this->getDoctrine()->getManager();
                $encodedPassword=$encoder->encodePassword($employee->getMotdepasse(),$salt);
                $employee->setMotdepasse($this->pwdEncoder->encodePassword($employee,$employee->getMotdepasse()));
                $employee->setRoles(['ROLE_EMPLOYER']);
                $entityManager->persist($employee);
                $entityManager->flush();

                return $this->redirectToRoute('indexhome');
            }}

        return $this->render('employee_front/new.html.twig', [
            'employee' => $employee,
            'form' => $form->createView(),
        ]);
    }
}